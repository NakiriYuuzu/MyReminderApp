package net.yuuzu.myreminder.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.yuuzu.myreminder.domain.model.Note
import net.yuuzu.myreminder.domain.repository.NoteRepository
import net.yuuzu.myreminder.domain.util.NoteOrder
import net.yuuzu.myreminder.domain.util.OrderType


class GetNotes (
             private val repository: NoteRepository
        ){
       operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>> {
           return repository.getNotes().map {notes->
               when(noteOrder.orderType){
                   is OrderType.Ascending->{
                        when(noteOrder){
                            is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                            is NoteOrder.Date -> notes.sortedBy { it.timesStamp }
                            is NoteOrder.Color -> notes.sortedBy { it.color }
                        }
                   }
                   is OrderType.Descending->{
                       when(noteOrder){
                           is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                           is NoteOrder.Date -> notes.sortedByDescending { it.timesStamp }
                           is NoteOrder.Color -> notes.sortedByDescending { it.color }
                       }
                   }
               }
           }
       }
}