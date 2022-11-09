package com.smhvincent.moo_d.moodlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.smhvincent.moo_d.MoodReminderWorker
import com.smhvincent.moo_d.database.Mood
import com.smhvincent.moo_d.database.MoodDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class MoodListViewModel(
    val database: MoodDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var mood = MutableLiveData<Mood?>()

    val moods = database.getAllMoods()

    init {
        initializeMood()
    }

    private fun initializeMood() {
        viewModelScope.launch {
            mood.value = getLastMoodFromDatabase()
        }
    }

    private suspend fun getLastMoodFromDatabase(): Mood? = database.getLastMood()

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun update(mood: Mood) {
        withContext(Dispatchers.IO) {
            database.update(mood)
        }
    }

    private suspend fun insert(mood: Mood) {
        withContext(Dispatchers.IO) {
            database.insert(mood)
        }
    }

    fun onUpdate(mood: Mood) {
        viewModelScope.launch {
            update(mood)
        }
    }

    fun onInsert(mood: Mood) {
        viewModelScope.launch {
            insert(mood)
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()

            mood.value = null
        }
    }


}