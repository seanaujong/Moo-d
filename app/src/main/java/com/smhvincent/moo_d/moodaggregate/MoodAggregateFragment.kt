package com.smhvincent.moo_d.moodaggregate

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.ViewModelProvider
import com.smhvincent.moo_d.R
import com.smhvincent.moo_d.database.MoodDatabase
import com.smhvincent.moo_d.databinding.FragmentMoodAggregateBinding
import kotlin.math.ceil


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MoodAggregateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMoodAggregateBinding =
            FragmentMoodAggregateBinding.inflate(inflater, container, false)

        val application = requireActivity().application

        val dataSource = MoodDatabase.getInstance(application).moodDatabaseDao
        val viewModelFactory = MoodAggregateViewModelFactory(dataSource)

        val moodAggregateViewModel =
            ViewModelProvider(this, viewModelFactory)[MoodAggregateViewModel::class.java]

        moodAggregateViewModel.average.observe(viewLifecycleOwner) { average ->
            val roundup = ceil(average).toInt()
            val text = when (roundup) {
                1 -> getString(com.smhvincent.moo_d.R.string.average_1)
                2 -> getString(com.smhvincent.moo_d.R.string.average_2)
                3 -> getString(com.smhvincent.moo_d.R.string.average_3)
                4 -> getString(com.smhvincent.moo_d.R.string.average_4)
                5 -> getString(com.smhvincent.moo_d.R.string.average_5)
                else -> ""
            }
            val image = when (roundup) {
                1 -> R.drawable.cow_1
                2 -> R.drawable.cow_2
                3 -> R.drawable.cow_3
                4 -> R.drawable.cow_4
                5 -> R.drawable.cow_5
                else -> R.drawable.cow_1
            }
            binding.firstAggregationTv.text =
                getString(com.smhvincent.moo_d.R.string.first_aggregation_message, text)
            binding.secondAggregationIv.setImageResource(image)
        }

        moodAggregateViewModel.exercise.observe(viewLifecycleOwner) { exercise ->
            binding.thirdAggregationTv.text =
                getString(com.smhvincent.moo_d.R.string.third_aggregation_message, exercise)
        }

        moodAggregateViewModel.func.observe(viewLifecycleOwner) { func ->
            binding.fourthAggregationTv.text =
                getString(com.smhvincent.moo_d.R.string.fourth_aggregation_message, func)
        }

        val scaleDown: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            binding.secondAggregationIv,
            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
            PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        )
        scaleDown.duration = 310

        scaleDown.repeatCount = ObjectAnimator.INFINITE
        scaleDown.repeatMode = ObjectAnimator.REVERSE
        scaleDown.interpolator = FastOutSlowInInterpolator()

        scaleDown.start()

        return binding.root

    }
}