package net.yuuzu.myreminder.domain.use_case

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNotes: DeleteNotes,
    val addNote: AddNotes,
    val getNote: GetNote
)