package net.yuuzu.myreminder.presentation.notes

import net.yuuzu.myreminder.domain.model.Note
import net.yuuzu.myreminder.domain.util.NoteOrder
import net.yuuzu.myreminder.domain.util.OrderType

data class NotesState(
    val notes : List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean = false
)
