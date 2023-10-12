import java.util.*

class Archive<T : Any>(val name: String) {
    private val notes: MutableList<T> = mutableListOf()

    fun addNote(note: T) {
        notes.add(note)
    }

    fun getNotes(): List<T> {
        return notes.toList()
    }

    fun archiveSize(): Int {
        return notes.size
    }

    fun deleteNoteFromArchive(note: T) {
        notes.remove(note)
    }

    fun viewNotes() {
        if (notes.isEmpty()) {
            println("Архив $name пуст.")
        } else {
            println("Список заметок в архиве $name:")
            for ((index, note) in notes.withIndex()) {
                println("$index. $note")
            }
        }
    }

    fun containsNote(note: T): Boolean {
        return notes.contains(note)
    }

    fun addNotesToExistingArchive(archive: Archive<Note>) {
        val scanner = Scanner(System.`in`)

        while (true) {
            print("Введите название заметки (для выхода введите 'выход'): ")
            val noteTitle = scanner.nextLine()

            if (noteTitle.toLowerCase() == "выход") {
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
            println("Заметка успешно добавлена в архив $archive.name.")
        }
    }
}