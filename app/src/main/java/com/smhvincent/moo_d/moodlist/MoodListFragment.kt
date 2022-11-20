package com.smhvincent.moo_d.moodlist

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.smhvincent.moo_d.database.MoodDatabase
import com.smhvincent.moo_d.databinding.FragmentMoodListBinding

class MoodListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMoodListBinding = FragmentMoodListBinding.inflate(
            inflater, container, false
        )

        val application = requireActivity().application

        val dataSource = MoodDatabase.getInstance(application).moodDatabaseDao

        val viewModelFactory = MoodListViewModelFactory(dataSource, application)

        val moodListViewModel =
            ViewModelProvider(this, viewModelFactory)[MoodListViewModel::class.java]

        // recyclerview

        val manager = GridLayoutManager(activity, 2)
        binding.moodList.layoutManager = manager

        val adapter = MoodAdapter(MoodListener { moodId ->
            val action = MoodListFragmentDirections.actionMoodListFragmentToMoodDetailFragment()
            action.moodKey = moodId
            findNavController().navigate(action)
        })
        binding.moodList.adapter = adapter

        moodListViewModel.moods.observe(viewLifecycleOwner) { list ->
            adapter.submitMoodList(list)
            binding.navAggregateButton.visibility =
                if (list.isEmpty()) View.INVISIBLE else View.VISIBLE
            if (list.isEmpty()) animate(binding.navNewMoodButton) else animate(binding.navAggregateButton)
        }

        // buttons

        binding.navNewMoodButton.setOnClickListener {
            findNavController().navigate(MoodListFragmentDirections.actionMoodListFragmentToReflectionFragment())
        }

        binding.navAggregateButton.setOnClickListener {
            findNavController().navigate(MoodListFragmentDirections.actionMoodListFragmentToAggregateFragment())
        }

        return binding.root
    }

    private fun animate(view: View) {
        ObjectAnimator.ofPropertyValuesHolder(
            view,
            PropertyValuesHolder.ofFloat("scaleX", 1.1f),
            PropertyValuesHolder.ofFloat("scaleY", 1.1f)
        ).apply {
            duration = 310
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = FastOutSlowInInterpolator()
            start()
        }
    }
}