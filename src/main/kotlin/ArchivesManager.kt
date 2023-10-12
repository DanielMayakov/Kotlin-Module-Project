class ArchivesManager {
    private val archives: MutableList<Archive<Note>> = mutableListOf()

    fun addArchive(archive: Archive<Note>) {
        archives.add(archive)
    }

    fun getArchive(index: Int): Archive<Note>? {
        if (index in archives.indices) {
            return archives[index]
        }
        return null
    }

    fun getArchiveContainingNote(note: Note): Archive<Note>? {
        for (archive in archives) {
            if (archive.containsNote(note)) {
                return archive
            }
        }
        return null
    }

    fun getArchives(): List<Archive<Note>> {
        return archives
    }

    fun viewArchives() {
        if (archives.isEmpty()) {
            println("Список архивов пуст.")
        } else {
            println("Список архивов:")
            for ((index, archive) in archives.withIndex()) {
                println("$index. Архив ${archive.name}")
            }
        }
    }
}