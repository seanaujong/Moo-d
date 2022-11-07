package com.smhvincent.moo_d.moodaggregate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.smhvincent.moo_d.R
import com.smhvincent.moo_d.database.MoodDatabase
import com.smhvincent.moo_d.databinding.FragmentMoodAggregateBinding
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MoodAggregateFragment : Fragment() {

    private var ratingMode by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args by navArgs<MoodAggregateFragmentArgs>()
        ratingMode = args.rating
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMoodAggregateBinding =
            FragmentMoodAggregateBinding.inflate(inflater, container, false)

        val application = requireActivity().application
        val arguments = MoodAggregateFragmentArgs.fromBundle(requireArguments())

        val dataSource = MoodDatabase.getInstance(application).moodDatabaseDao
        // TODO: send in correct mood key
        val viewModelFactory = MoodAggregateViewModelFactory(dataSource, 0)

        val moodAggregateViewModel =
            ViewModelProvider(this, viewModelFactory)[MoodAggregateViewModel::class.java]



        return binding.root

    }
}