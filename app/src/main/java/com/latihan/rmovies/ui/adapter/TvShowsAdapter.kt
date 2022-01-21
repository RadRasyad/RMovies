package com.latihan.rmovies.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.ItemRowBinding
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.ui.detail.DetailActivity

class TvShowsAdapter : PagedListAdapter<TvShowsEntity, TvShowsAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowsEntity>() {
            override fun areItemsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shows = getItem(position)
        if (shows!=null) {
            holder.bindViewHolder(shows)
        }
    }

    inner class ViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val poster = binding.ivPoster

        fun bindViewHolder(listShows: TvShowsEntity) {
            binding.tvTitle.text = listShows.name
            binding.tvStarRate.text = listShows.voteAverage.toString()
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${listShows.posterPath}")
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.ic_error)
                .into(poster)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_SHOW, listShows.id.toString())
                itemView.context.startActivity(intent)
            }
        }
    }

}