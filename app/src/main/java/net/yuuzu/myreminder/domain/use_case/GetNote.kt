package net.yuuzu.myreminder.domain.use_case

import net.yuuzu.myreminder.domain.model.Note
import net.yuuzu.myreminder.domain.repository.NoteRepository

class GetNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return noteRepository.getNoteById(id)
    }
}