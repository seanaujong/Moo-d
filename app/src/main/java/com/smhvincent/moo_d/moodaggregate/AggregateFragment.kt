package com.smhvincent.moo_d.moodaggregate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.smhvincent.moo_d.R
import com.smhvincent.moo_d.databinding.FragmentUserAggregateBinding
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AggregateFragment : Fragment() {

    private var _binding: FragmentUserAggregateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MoodAggregateViewModel>()

    private var ratingMode by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args by navArgs<AggregateFragmentArgs>()
        ratingMode = args.rating
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserAggregateBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.firstAggregationTv.text = getString(R.string.first_aggregation_message, ratingMode)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}