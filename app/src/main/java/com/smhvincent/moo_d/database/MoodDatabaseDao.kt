package com.smhvincent.moo_d.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MoodDatabaseDao {
    @Insert
    suspend fun insert(mood: Mood)

    @Update
    suspend fun update(mood: Mood)

    @Query("SELECT * FROM mood_table WHERE moodId = :key")
    suspend fun get(key: Long): Mood?

    @Query("DELETE FROM mood_table")
    suspend fun clear()

    @Query("SELECT * FROM mood_table ORDER BY moodId DESC")
    fun getAllMoods(): LiveData<List<Mood>>

    @Query("SELECT * FROM mood_table ORDER BY moodId DESC LIMIT 1")
    suspend fun getLastMood(): Mood?

    @Query("SELECT * FROM mood_table WHERE moodId = :key")
    fun getWithLiveData(key: Long): LiveData<Mood>

    @Query("SELECT AVG(rating) FROM mood_table")
    suspend fun getRatingAverage(): Double

    @Query("SELECT COUNT(notes) FROM mood_table WHERE notes LIKE '%exercise%'")
    suspend fun getExerciseCount(): Long

    @Query("SELECT COUNT(notes) FROM mood_table WHERE notes LIKE '%fun%'")
    suspend fun getFunCount(): Long
}