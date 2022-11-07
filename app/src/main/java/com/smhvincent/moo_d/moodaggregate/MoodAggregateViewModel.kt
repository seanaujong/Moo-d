package com.smhvincent.moo_d.moodaggregate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smhvincent.moo_d.database.MoodDatabaseDao
import kotlinx.coroutines.launch

class MoodAggregateViewModel(
    val database: MoodDatabaseDao
): ViewModel() {

    private var _average = MutableLiveData<Long>()
    val average: LiveData<Long>
        get() = _average

    init {
        viewModelScope.launch {
            _average.value = database.getRatingAverage()
        }
    }
}