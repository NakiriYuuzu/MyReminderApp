package net.yuuzu.myreminder.domain.use_case

import net.yuuzu.myreminder.domain.model.Note
import net.yuuzu.myreminder.domain.repository.NoteRepository

class DeleteNotes(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note){
       repository.deleteNote(note)
    }
}