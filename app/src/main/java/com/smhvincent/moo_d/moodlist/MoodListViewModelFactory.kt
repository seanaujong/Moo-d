package com.smhvincent.moo_d.moodlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smhvincent.moo_d.database.MoodDatabaseDao
import com.smhvincent.moo_d.reflection.ReflectionViewModel

class MoodListViewModelFactory(
    private val dataSource: MoodDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodListViewModel::class.java)) {
            return MoodListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}