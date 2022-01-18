package com.latihan.rmovies.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.ItemRowBinding
import com.latihan.rmovies.model.remote.ApiResponse
import com.latihan.rmovies.model.remote.response.Movies
import com.latihan.rmovies.ui.detail.DetailActivity

class TestAdapter : RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    private var listMovies: List<Movies> = emptyList()

    fun add(movies: ApiResponse<List<Movies>>) {
        this.listMovies = movies.body
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(listMovies[position])
    }

    inner class ViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val poster = binding.ivPoster

        fun bindViewHolder(listMovies: Movies) {
            binding.tvTitle.text = listMovies.title
            binding.tvStarRate.text = listMovies.voteAverage.toString()
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${listMovies.posterPath}")
                .placeholder(R.color.placeholder)
                .error(R.drawable.ic_error)
                .into(poster)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIE, listMovies.id.toString())
                itemView.context.startActivity(intent)
            }
        }
    }

}