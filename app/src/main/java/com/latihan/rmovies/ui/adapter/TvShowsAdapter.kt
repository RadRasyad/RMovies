package com.latihan.rmovies.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.ItemRowBinding
import com.latihan.rmovies.model.entity.Item
import com.latihan.rmovies.ui.detail.DetailActivity

class TvShowsAdapter: RecyclerView.Adapter<TvShowsAdapter.ViewHolder>() {

    private var listMovies: List<Item> = emptyList()

    fun showsAdapter(movies: List<Item>){
        this.listMovies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(listMovies[position])
    }

    inner class ViewHolder(private val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {
        private val poster = binding.ivPoster

        fun bindViewHolder(listMovies: Item) {
            binding.tvTitle.text = listMovies.name
            binding.tvStarRate.text = listMovies.voteAverage.toString()
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${listMovies.posterPath}")
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.ic_error)
                .into(poster)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_SHOW, listMovies.id.toString())
                itemView.context.startActivity(intent)
            }
        }
    }


}