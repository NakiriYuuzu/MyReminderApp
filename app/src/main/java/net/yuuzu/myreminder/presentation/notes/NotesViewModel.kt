package net.yuuzu.myreminder.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import net.yuuzu.myreminder.domain.model.Note
import net.yuuzu.myreminder.domain.use_case.NoteUseCases
import net.yuuzu.myreminder.domain.util.NoteOrder
import net.yuuzu.myreminder.domain.util.OrderType
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel(){

    private val _state = mutableStateOf(NotesState())
    val state:State<NotesState> = _state
    private var recentDeleteNotes : Note? =  null
    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }
    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order->{
              if(state.value.noteOrder::class == event.noteOrder::class &&
                      state.value.noteOrder.orderType == event.noteOrder.orderType){
                  return
              }
                getNotes(event.noteOrder)
            }

            is NotesEvent.DeleteNote->{
                viewModelScope.launch {
                    noteUseCases.deleteNotes(event.note)
                    recentDeleteNotes = event.note
                }
            }

            is NotesEvent.RestoreNote->{
                viewModelScope.launch {
                  noteUseCases.addNote(recentDeleteNotes ?: return@launch)
                    recentDeleteNotes = null
                }
            }

            is NotesEvent.ToggleOrderSection->{
                   _state.value = state.value.copy(
                       isOrderSectionVisible = !state.value.isOrderSectionVisible
                   )
            }
        }
    }

    private fun getNotes(notesOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(notesOrder).onEach {notes->
            _state.value = state.value.copy(
                notes = notes,
                noteOrder = notesOrder
            )
        }.launchIn(viewModelScope)
    }
}