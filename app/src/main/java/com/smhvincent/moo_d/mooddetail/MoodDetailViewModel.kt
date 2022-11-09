package com.smhvincent.moo_d.mooddetail

import android.util.Log
import androidx.lifecycle.*
import com.smhvincent.moo_d.database.Mood
import com.smhvincent.moo_d.database.MoodDatabaseDao
import kotlinx.coroutines.launch

class MoodDetailViewModel(
    dataSource: MoodDatabaseDao,
    moodKey: Long = 0L
) : ViewModel() {

    val database = dataSource

    private var _mood = MutableLiveData<Mood>()
    val mood: LiveData<Mood>
        get() = _mood

    init {
        viewModelScope.launch {
            Log.d("saujong", moodKey.toString())
            _mood.value = database.get(moodKey)
        }
    }
}