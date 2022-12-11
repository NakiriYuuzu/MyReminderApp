package net.yuuzu.myreminder.data.data_source

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import net.yuuzu.myreminder.domain.model.Note

@Dao
interface ReminderDao {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}