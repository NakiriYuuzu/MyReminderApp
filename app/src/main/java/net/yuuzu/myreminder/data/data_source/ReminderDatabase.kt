package net.yuuzu.myreminder.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import net.yuuzu.myreminder.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class ReminderDatabase : RoomDatabase() {
    abstract val reminderDao:ReminderDao

    companion object{
        const val DATABASE_NAME = "reminder_db"
    }
}