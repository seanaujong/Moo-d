package com.smhvincent.moo_d.moodlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.smhvincent.moo_d.R
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

        val manager = GridLayoutManager(activity, 3)
        binding.moodList.layoutManager = manager

        val adapter = MoodAdapter(MoodListener { moodId ->
            val action = MoodListFragmentDirections.actionMoodListFragmentToMoodDetailFragment()
            action.moodKey = moodId
            findNavController().navigate(action)
        })
        binding.moodList.adapter = adapter

        moodListViewModel.moods.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitMoodList(it)
            }
        }

        // buttons

        binding.navNewMoodButton.setOnClickListener {
            Log.d("saujong", "navigating")
            findNavController().navigate(MoodListFragmentDirections.actionMoodListFragmentToReflectionFragment())
        }

        return binding.root
    }
}