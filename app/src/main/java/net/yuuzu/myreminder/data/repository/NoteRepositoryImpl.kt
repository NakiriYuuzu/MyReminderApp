package net.yuuzu.myreminder.data.repository

import kotlinx.coroutines.flow.Flow
import net.yuuzu.myreminder.data.data_source.ReminderDao
import net.yuuzu.myreminder.domain.model.Note
import net.yuuzu.myreminder.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val reminderDao: ReminderDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return reminderDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return reminderDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        reminderDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        reminderDao.deleteNote(note)
    }
}
