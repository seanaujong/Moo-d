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
            binding.firstAggregationTv.text = getString(R.string.first_aggregation_message, average)
        }

        return binding.root

    }
}