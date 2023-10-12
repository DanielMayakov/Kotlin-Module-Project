data class Note(var title: String, var content: String) {
    override fun toString(): String {
        return "$title: $content"
    }
}