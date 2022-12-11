package net.yuuzu.myreminder.domain.use_case

import net.yuuzu.myreminder.domain.model.InvalidNoteException
import net.yuuzu.myreminder.domain.model.Note
import net.yuuzu.myreminder.domain.repository.NoteRepository
import kotlin.jvm.Throws


class AddNotes(
    private val notesRepository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't empty")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't empty")
        }
        notesRepository.insertNote(note)
    }
}