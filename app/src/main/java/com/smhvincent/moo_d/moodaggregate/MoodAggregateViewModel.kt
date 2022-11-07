package com.smhvincent.moo_d.moodaggregate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smhvincent.moo_d.database.MoodDatabaseDao

class MoodAggregateViewModel(
    dataSource: MoodDatabaseDao,
    private val moodKey: Long = 0L
): ViewModel() {

    // number of days exercised this week
    private var _exerciseDays = MutableLiveData<Int>()
    val exerciseDays: LiveData<Int>
        get() = _exerciseDays

    // number of days socializing this week
    private var _socialDays = MutableLiveData<Int>()
    val socialDays: LiveData<Int>
        get() = _socialDays
}