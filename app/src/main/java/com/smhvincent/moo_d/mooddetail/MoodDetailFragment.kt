package com.smhvincent.moo_d.mooddetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smhvincent.moo_d.database.MoodDatabase
import com.smhvincent.moo_d.databinding.FragmentMoodDetailBinding

class MoodDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentMoodDetailBinding = FragmentMoodDetailBinding.inflate(
            inflater, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = MoodDetailFragmentArgs.fromBundle(requireArguments())

        // Create an instance of the ViewModel Factory.
        val dataSource = MoodDatabase.getInstance(application).moodDatabaseDao
        val viewModelFactory = MoodDetailViewModelFactory(arguments.moodKey, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val moodDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory
            )[MoodDetailViewModel::class.java]

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.moodDetailViewModel = moodDetailViewModel

        // binding.setLifecycleOwner(this)
        binding.lifecycleOwner = this

        return binding.root
    }
}