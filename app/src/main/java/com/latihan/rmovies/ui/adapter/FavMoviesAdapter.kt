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
import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.ui.detail.DetailActivity

class FavMoviesAdapter : PagedListAdapter<FavoriteMoviesEntity, FavMoviesAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies!=null) {
            holder.bindViewHolder(movies)
        }
    }

    inner class ViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val poster = binding.ivPoster

        fun bindViewHolder(listMovies: FavoriteMoviesEntity?) {
            binding.tvTitle.text = listMovies?.title
            binding.tvStarRate.text = listMovies?.voteAverage.toString()
            Glide.with(itemView.context)
                .load(imgUrl+"${listMovies?.posterPath}")
                .placeholder(R.color.placeholder)
                .error(R.drawable.ic_error)
                .into(poster)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_FAV_MOVIE, listMovies?.id.toString())
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        const val imgUrl = "https://image.tmdb.org/t/p/w500"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteMoviesEntity>() {
            override fun areItemsTheSame(oldItem: FavoriteMoviesEntity, newItem: FavoriteMoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteMoviesEntity, newItem: FavoriteMoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}