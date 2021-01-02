package com.example.football.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.football.databinding.NewsListItemBinding
import com.example.football.domain.NewsModel

class NewsAdapter :
    ListAdapter<NewsModel, NewsAdapter.ViewHolder>(DiffCallback) {

    // viewHolder
    class ViewHolder(private var binding: NewsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsModel) {
            binding.news = news
            binding.executePendingBindings()
        }

        // inflate layout from viewHolder
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewsListItemBinding.inflate(
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
object DiffCallback : DiffUtil.ItemCallback<NewsModel>() {
    override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
        return oldItem.title == newItem.title
    }

}