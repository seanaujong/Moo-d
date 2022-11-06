package com.smhvincent.moo_d.moodaggregate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoodAggregateViewModel: ViewModel() {

    // number of days exercised this week
    private var _exerciseDays = MutableLiveData<Int>()
    val exerciseDays: LiveData<Int>
        get() = _exerciseDays

    // number of days socializing this week
    private var _socialDays = MutableLiveData<Int>()
    val socialDays: LiveData<Int>
        get() = _socialDays
}