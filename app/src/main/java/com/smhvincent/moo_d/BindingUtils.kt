package com.smhvincent.moo_d

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.smhvincent.moo_d.database.Mood

@BindingAdapter("moodImage")
fun ImageView.setMoodImage(item: Mood?) {
    item?.let {
//        setImageResource(when (item.sleepQuality) {
//            0 -> R.drawable.ic_sleep_0
//            1 -> R.drawable.ic_sleep_1
//            2 -> R.drawable.ic_sleep_2
//            3 -> R.drawable.ic_sleep_3
//            4 -> R.drawable.ic_sleep_4
//            5 -> R.drawable.ic_sleep_5
//            else -> R.drawable.ic_sleep_active
//        })
    }
}

@BindingAdapter("moodQualityString")
fun TextView.setMoodQualityString(item: Mood?) {
    item?.let {
        // TODO: implement
        text = "hahahaha"
    }
}
