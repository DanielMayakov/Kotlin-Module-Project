import java.util.*

class MenuScreen {
    private val scanner = Scanner(System.`in`)
    private val archivesManager = ArchivesManager()

    fun showArchiveMenu() {
        while (true) {
            println("Меню архивов:")
            println("1. Создать архив")
            println("2. Просмотреть архивы")
            println("3. Взаимодействие с архивами")
            println("4. Выход")
            print("Выберите пункт меню: ")

            val userInput = readIntFromUser()
            when (userInput) {
                1 -> createArchive()
                2 -> viewArchives()
                3 -> interactWithArchives()
                4 -> exit()
                else -> println("Некорректный ввод. Пожалуйста, выберите существующий пункт.")
            }
        }
    }

    private fun createArchive() {
        print("Введите название архива: ")
        val archiveName = scanner.nextLine()
        if (archiveName.isBlank()) {
            println("Название архива не может быть пустым.")
            return
        }

        val archive = Archive<Note>(archiveName)
        archivesManager.addArchive(archive)
        println("Архив '$archiveName' создан.")
        addNotesToArchive(archive)
    }

    private fun addNotesToArchive(archive: Archive<Note>) {
        while (true) {
            print("Введите название заметки (для выхода введите 'exit'): ")
            val noteTitle = scanner.nextLine()
            if (noteTitle.toLowerCase() == "exit") {
                break
            }

            if (noteTitle.isBlank()) {
                println("Название заметки не может быть пустым.")
                continue
            }

            print("Введите содержимое заметки: ")
            val noteContent = scanner.nextLine()
            if (noteContent.isBlank()) {
                println("Содержимое заметки не может быть пустым.")
                continue
            }

            val note = Note(noteTitle, noteContent)
            archive.addNote(note)
        }
    }

    private fun viewArchives() {
        archivesManager.viewArchives()
        print("Выберите номер архива для просмотра заметок (для выхода введите 'exit'): ")

        val userInput = scanner.nextLine()
        if (userInput.toLowerCase() == "exit") {
            return
        }

        val archiveIndex = userInput.toIntOrNull()
        if (archiveIndex != null) {
            val archive = archivesManager.getArchive(archiveIndex)
            if (archive != null) {
                viewArchiveNotes(archive)
            } else {
                println("Архив с номером $archiveIndex не найден.")
            }
        } else {
            println("Некорректный ввод. Пожалуйста, введите число.")
        }
    }

    private fun viewArchiveNotes(archive: Archive<Note>) {
        archive.viewNotes()
        print("Выберите номер заметки для просмотра (для выхода введите 'exit'): ")

        val userInput = scanner.nextLine()
        if (userInput.toLowerCase() == "exit") {
            return
        }

        val noteIndex = userInput.toIntOrNull()
        if (noteIndex != null) {
            val notes = archive.getNotes()
            if (noteIndex in notes.indices) {
                val note = notes[noteIndex]
                println("Заметка '$note' выбрана.")
                interactWithNote(note)
            } else {
                println("Заметка с номером $noteIndex не найдена.")
            }
        } else {
            println("Некорректный ввод. Пожалуйста, введите число.")
        }
    }

    private fun interactWithNote(note: Note) {
        while (true) {
            println("Меню взаимодействия с заметкой:")
            println("1. Редактировать название заметки")
            println("2. Редактировать содержимое заметки")
            println("3. Удалить заметку")
            println("4. Вернуться к списку заметок")
            print("Выберите пункт меню: ")

            val userInput = readIntFromUser()
            when (userInput) {
                1 -> editNoteTitle(note)
                2 -> editNoteContent(note)
                3 -> deleteNoteFromArchive(note)
                4 -> return
                else -> println("Некорректный ввод. Пожалуйста, выберите существующий пункт.")
            }
        }
    }

    private fun editNoteTitle(note: Note) {
        print("Введите новое название заметки: ")
        val newTitle = scanner.nextLine()
        note.title = newTitle
        println("Название заметки изменено.")
    }

    private fun editNoteContent(note: Note) {
        print("Введите новое содержимое заметки: ")
        val newContent = scanner.nextLine()
        note.content = newContent
        println("Содержимое заметки изменено.")
    }

    private fun deleteNoteFromArchive(note: Note) {
        val archive = archivesManager.getArchiveContainingNote(note)
        if (archive != null) {
            archive.deleteNoteFromArchive(note)
            println("Заметка удалена из архива.")
        } else {
            println("Не удалось найти архив, содержащий данную заметку.")
        }
    }

    private fun interactWithArchives() {
        val archives = archivesManager.getArchives()
        if (archives.isEmpty()) {
            println("Список архивов пуст.")
            return
        }

        println("Список архивов:")
        for ((index, archive) in archives.withIndex()) {
            println("$index. Архив ${archive.name}")
        }

        print("Выберите номер архива для выполнения операции (для выхода введите 'exit'): ")

        val userInput = scanner.nextLine()
        if (userInput.toLowerCase() == "exit") {
            return
        }

        val archiveIndex = userInput.toIntOrNull()
        if (archiveIndex != null) {
            val archives = archivesManager.getArchives()
            if (archiveIndex in archives.indices) {
                val archive = archives[archiveIndex]
                println("Вы выбрали архив '{archive.name}'.")
                viewArchiveNotes(archive)
            } else {
                println("Архив с номером archiveIndex не найден.")
            }
        } else {
            println("Некорректный ввод. Пожалуйста, введите число.")
        }
    }

    private fun exit() {
        println("До свидания!")
        System.exit(0)
    }

    private fun readIntFromUser(): Int {
        while (true) {
            try {
                return scanner.nextLine().toInt()
            } catch (e: NumberFormatException) {
                println("Некорректный ввод. Пожалуйста, введите число.")
            }
        }
    }
}

