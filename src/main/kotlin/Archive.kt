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
}