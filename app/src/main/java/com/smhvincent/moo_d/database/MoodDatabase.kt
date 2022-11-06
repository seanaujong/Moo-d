package com.smhvincent.moo_d.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Mood::class], version = 1, exportSchema = false)
abstract class MoodDatabase : RoomDatabase() {

    abstract val moodDatabaseDao: MoodDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: MoodDatabase? = null

        fun getInstance(context: Context): MoodDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MoodDatabase::class.java,
                        "mood_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}