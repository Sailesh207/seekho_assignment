package com.example.seekho.UILayer.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seekho.DataLayer.DataClass.Anime
import com.example.seekho.R

class AnimeSeriesListAdapter(private var animeSeriesList : MutableList<Anime>, private val onItemClick : (Anime) -> Unit) : RecyclerView.Adapter<AnimeSeriesListAdapter.AnimeSeriesViewHolder>() {

    inner class AnimeSeriesViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val animeImage : ImageView = view.findViewById(R.id.anime_image)
        val animeTitle : TextView = view.findViewById(R.id.anime_name)
        val animeEpisodes : TextView = view.findViewById(R.id.anime_episodes)
        val animeRating : TextView = view.findViewById(R.id.anime_ratings)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeSeriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        return AnimeSeriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animeSeriesList.size
    }

    fun updateList(animeList : List<Anime>){
        animeSeriesList.addAll(animeList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AnimeSeriesViewHolder, position: Int) {
        val currentAnime = animeSeriesList[position]
        holder.animeTitle.text = currentAnime.title
        holder.animeEpisodes.text = "Episodes: ${currentAnime.episodes}"
        holder.animeRating.text = "Ratings: ${currentAnime.rating}"
        Glide.with(holder.itemView).load(currentAnime.imageUrl).into(holder.animeImage)

        holder.itemView.setOnClickListener{
            onItemClick(currentAnime)
        }
    }
}