package com.smhvincent.moo_d.mooddetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smhvincent.moo_d.database.MoodDatabaseDao

class MoodDetailViewModelFactory(
    private val moodKey: Long,
    private val dataSource: MoodDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodDetailViewModel::class.java)) {
            return MoodDetailViewModel(dataSource, moodKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
