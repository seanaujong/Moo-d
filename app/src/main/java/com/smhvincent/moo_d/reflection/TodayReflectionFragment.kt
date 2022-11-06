package com.smhvincent.moo_d.reflection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.smhvincent.moo_d.R
import com.smhvincent.moo_d.reflection.TodayReflectionFragmentDirections.actionTodayReflectionFragmentToAggregateFragment
import com.smhvincent.moo_d.databinding.FragmentTodayReflectionBinding
import com.smhvincent.moo_d.moodaggregate.MoodAggregateViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TodayReflectionFragment : Fragment() {

    private var _binding: FragmentTodayReflectionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MoodAggregateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTodayReflectionBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSubmit.setOnClickListener {
            val rating = when (binding.q1Ratings.checkedRadioButtonId) {
                R.id.option_rating_1 -> 1
                R.id.option_rating_2 -> 2
                R.id.option_rating_3 -> 3
                R.id.option_rating_4 -> 4
                R.id.option_rating_5 -> 5
                else -> 5
            }
            val action = actionTodayReflectionFragmentToAggregateFragment().apply {
                this.rating = rating
            }
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}