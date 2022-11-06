package com.smhvincent.moo_d.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_table")
data class Mood(
    @PrimaryKey(autoGenerate = true)
    var moodId: Long = 0L,

    @ColumnInfo(name = "rating")
    var rating: Int = -1,

    @ColumnInfo(name = "notes")
    var notes: String = "",

    @ColumnInfo(name = "time")
    var time: Long = System.currentTimeMillis()
)