package com.example.football.ui.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.football.databinding.LeagueTableListItemBinding
import com.example.football.domain.LeagueTableModel

class LeagueTableAdapter :
    ListAdapter<LeagueTableModel, LeagueTableAdapter.ViewHolder>(DiffCallback) {

    // viewHolder
    class ViewHolder(private var binding: LeagueTableListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(leagues: LeagueTableModel) {
            binding.leagues = leagues
            binding.executePendingBindings()
        }

        // inflate layout from viewHolder
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LeagueTableListItemBinding.inflate(
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
object DiffCallback : DiffUtil.ItemCallback<LeagueTableModel>() {
    override fun areItemsTheSame(oldItem: LeagueTableModel, newItem: LeagueTableModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: LeagueTableModel, newItem: LeagueTableModel): Boolean {
        return oldItem.name == newItem.name
    }

}