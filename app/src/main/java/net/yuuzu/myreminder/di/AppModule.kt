package net.yuuzu.myreminder.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.yuuzu.myreminder.data.data_source.ReminderDatabase
import net.yuuzu.myreminder.data.repository.NoteRepositoryImpl
import net.yuuzu.myreminder.domain.repository.NoteRepository
import net.yuuzu.myreminder.domain.use_case.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDataBase(application: Application): ReminderDatabase {
        return Room.databaseBuilder(
            application,
            ReminderDatabase::class.java,
            ReminderDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(reminderDatabase: ReminderDatabase): NoteRepository {
        return NoteRepositoryImpl(reminderDatabase.reminderDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(noteRepository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(noteRepository),
            deleteNotes = DeleteNotes(noteRepository),
            addNote = AddNotes(noteRepository),
            getNote = GetNote(noteRepository)
        )
    }
}