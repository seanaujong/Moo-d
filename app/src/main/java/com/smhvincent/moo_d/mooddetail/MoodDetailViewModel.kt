package com.smhvincent.moo_d.mooddetail

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.smhvincent.moo_d.database.Mood
import com.smhvincent.moo_d.database.MoodDatabaseDao

class MoodDetailViewModel(
    dataSource: MoodDatabaseDao,
    moodKey: Long = 0L
) : ViewModel() {

    val database = dataSource

    private val mood = MediatorLiveData<Mood>()
    fun getMood() = mood

    init {
        mood.addSource(database.getWithLiveData(moodKey), mood::setValue)
    }
}