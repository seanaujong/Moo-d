package com.smhvincent.moo_d.moodaggregate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smhvincent.moo_d.database.MoodDatabaseDao

class MoodAggregateViewModelFactory(
    private val dataSource: MoodDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodAggregateViewModel::class.java)) {
            return MoodAggregateViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}