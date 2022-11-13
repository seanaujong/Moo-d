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

    private var _average = MutableLiveData<Double>()
    val average: LiveData<Double>
        get() = _average

    private var _exercise = MutableLiveData<Long>()
    val exercise: LiveData<Long>
        get() = _exercise

    init {
        viewModelScope.launch {
            _average.value = database.getRatingAverage()
            _exercise.value = database.getExerciseCount()
        }
    }
}