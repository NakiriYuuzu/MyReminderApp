package net.yuuzu.myreminder.presentation.notes

import net.yuuzu.myreminder.domain.model.Note
import net.yuuzu.myreminder.domain.util.NoteOrder

sealed class NotesEvent{
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}
