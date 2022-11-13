package com.smhvincent.moo_d.moodlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smhvincent.moo_d.database.Mood
import com.smhvincent.moo_d.databinding.ListItemMoodBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class MoodAdapter(
    val clickListener: MoodListener
) : ListAdapter<DataItem, RecyclerView.ViewHolder>(MoodDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun submitMoodList(list: List<Mood>?) {
        adapterScope.launch {
            val items = list?.map { DataItem.MoodItem(it) }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MoodViewHolder -> {
                val moodItem = getItem(position) as DataItem.MoodItem
                holder.bind(clickListener, moodItem.mood)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoodViewHolder.from(parent)
    }

    class MoodViewHolder private constructor(val binding: ListItemMoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: MoodListener, item: Mood) {
            binding.mood = item
            binding.clickListener = clickListener
            val date = SimpleDateFormat("MM/dd/yyyy").format(Date(item.time))
            val text = item.rating.toString() + " - " + date
            binding.title.text = text
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemMoodBinding.inflate(layoutInflater, parent, false)
                return MoodViewHolder(binding)
            }
        }
    }
}

class MoodDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }
}

class MoodListener(val clickListener: (moodId: Long) -> Unit) {
    fun onClick(mood: Mood) = clickListener(mood.moodId)
}

sealed class DataItem {
    data class MoodItem(val mood: Mood) : DataItem() {
        override val id = mood.moodId
    }

    abstract val id: Long
}
