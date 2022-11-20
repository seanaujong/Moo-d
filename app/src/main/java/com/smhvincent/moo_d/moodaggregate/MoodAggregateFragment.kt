package com.smhvincent.moo_d.moodaggregate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
                1 -> getString(R.string.average_1)
                2 -> getString(R.string.average_2)
                3 -> getString(R.string.average_3)
                4 -> getString(R.string.average_4)
                5 -> getString(R.string.average_5)
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
            binding.firstAggregationTv.text = getString(R.string.first_aggregation_message, text)
            binding.secondAggregationIv.setImageResource(image)
        }

        moodAggregateViewModel.exercise.observe(viewLifecycleOwner) { exercise ->
            binding.thirdAggregationTv.text =
                getString(R.string.third_aggregation_message, exercise)
        }

        moodAggregateViewModel.func.observe(viewLifecycleOwner) { func ->
            binding.fourthAggregationTv.text = getString(R.string.fourth_aggregation_message, func)
        }

        return binding.root

    }
}