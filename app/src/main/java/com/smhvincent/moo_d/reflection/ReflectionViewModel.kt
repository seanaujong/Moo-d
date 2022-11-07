package com.smhvincent.moo_d.reflection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smhvincent.moo_d.database.Mood
import com.smhvincent.moo_d.database.MoodDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReflectionViewModel(
    val database: MoodDatabaseDao
) : ViewModel() {

    fun onReflection(rating: Int, notes: String) {
        viewModelScope.launch {
            val mood = Mood(
                rating = rating,
                notes = notes
            )
            database.insert(mood)
        }
    }
}