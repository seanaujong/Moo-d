package com.smhvincent.moo_d.reflection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smhvincent.moo_d.R
import com.smhvincent.moo_d.database.MoodDatabase
import com.smhvincent.moo_d.databinding.FragmentReflectionBinding

class ReflectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentReflectionBinding =
            FragmentReflectionBinding.inflate(inflater, container, false)

        val application = requireActivity().application

        val dataSource = MoodDatabase.getInstance(application).moodDatabaseDao
        val viewModelFactory = ReflectionViewModelFactory(dataSource)

        val reflectionViewModel =
            ViewModelProvider(this, viewModelFactory)[ReflectionViewModel::class.java]

        // insert into database and go back to mood list screen
        binding.buttonSubmit.setOnClickListener {
            val rating = when (binding.q1Ratings.checkedRadioButtonId) {
                R.id.option_rating_1 -> 1
                R.id.option_rating_2 -> 2
                R.id.option_rating_3 -> 3
                R.id.option_rating_4 -> 4
                R.id.option_rating_5 -> 5
                else -> 5
            }

            reflectionViewModel.onReflection(rating, binding.textfieldInputMood.text.toString())

            findNavController().navigate(ReflectionFragmentDirections.actionReflectionFragmentToMoodListFragment())
        }

        return binding.root

    }
}