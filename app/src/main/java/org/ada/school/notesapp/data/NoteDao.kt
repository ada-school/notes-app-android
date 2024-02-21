package org.ada.school.notesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM Note WHERE color=:color")
    fun getAllByColor(color: String): List<Note>

    @Insert
    fun insertAll(vararg notes: Note)

    @Delete
    fun delete(note: Note)
}
