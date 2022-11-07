package com.smhvincent.moo_d.reflection

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smhvincent.moo_d.database.MoodDatabaseDao

class ReflectionViewModelFactory(
    private val dataSource: MoodDatabaseDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReflectionViewModel::class.java)) {
            return ReflectionViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}