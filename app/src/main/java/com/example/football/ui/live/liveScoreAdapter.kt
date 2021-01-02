package com.example.football.ui.live

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.football.databinding.LiveScoreListItemBinding
import com.example.football.domain.NetworkLiveScore

class LiveScoreAdapter :
    ListAdapter<NetworkLiveScore, LiveScoreAdapter.ViewHolder>(DiffCallback) {

    // viewHolder
    class ViewHolder(private var binding: LiveScoreListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NetworkLiveScore) {
            binding.liveScore = news
            binding.executePendingBindings()
        }

        // inflate layout from viewHolder
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LiveScoreListItemBinding.inflate(
                    layoutInflater, parent, false,
                )
                return ViewHolder(binding)
            }
        }
    }

    // layout inflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    // view Binder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}

// callback to compare content and item
object DiffCallback : DiffUtil.ItemCallback<NetworkLiveScore>() {
    override fun areItemsTheSame(oldItem: NetworkLiveScore, newItem: NetworkLiveScore): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: NetworkLiveScore, newItem: NetworkLiveScore): Boolean {
        return oldItem == newItem
    }

}