package org.ada.school.notesapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "color") val color: String,
    @ColumnInfo(name = "date") val createdAt: Date
) {
    constructor(text: String, color: String) : this(null, text, color, Date())
}

