package com.example.seekho.UILayer.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.seekho.DataLayer.DataClass.Anime
import com.example.seekho.DataLayer.RepositoryImpl.AnimeRepositoryImpl
import com.example.seekho.DataLayer.RetrofitInstance.RetrofitInstance
import com.example.seekho.DomainLayer.ViewModel.AnimeDetailsViewModel
import com.example.seekho.UILayer.Factory.AnimeViewModelFactory
import com.example.seekho.databinding.ActivityAnimeDetailsBinding
import com.google.gson.Gson

class AnimeDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAnimeDetailsBinding
    private lateinit var playerView : PlayerView
    private lateinit var viewModel : AnimeDetailsViewModel
    private lateinit var player: Player

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val animeApiService = RetrofitInstance.api
        val repository = AnimeRepositoryImpl(animeApiService)
        val factory = AnimeViewModelFactory(repository)
        viewModel = ViewModelProvider.create(this, factory)[AnimeDetailsViewModel::class.java]
        val animeGson : String? = intent.getStringExtra("anime")
        val anime = Gson().fromJson(animeGson, Anime::class.java)

        playerView = binding.playerView

        viewModel.fetchAnimeData(anime.mal_id)

        viewModel.getAnimeDetails.observe(this){ animeData ->
            Log.d("MainActivity", "$animeData")
            val youtubeId = animeData.trailer?.youtubeId
            Log.d("MainActivity", "$youtubeId")
            if (!youtubeId.isNullOrEmpty()) {
                Log.d("MainActivity", "$youtubeId")
                player = ExoPlayer.Builder(this@AnimeDetailsActivity).build()
                playerView.player = player
                playerView.visibility = View.VISIBLE
                val mediaItem = MediaItem.fromUri("https://www.youtube.com/watch?v=$youtubeId")
                player.setMediaItem(mediaItem)
                player.prepare()
                player.playWhenReady = true
            }

            binding.titleText.text = "Title: ${animeData.title}"
            binding.synopsisText.text = "Plot: ${animeData.synopsis}"
            binding.genresText.text = "Genres: ${animeData.genreList.joinToString(", ") { g -> g.name }}"
            binding.episodesText.text = "Episodes: ${animeData.episode}"
            binding.ratingText.text = "Rating: ${animeData.rating}"

        }

        viewModel.getCharacter.observe(this){ animeData ->
            Log.d("MainActivity", "$animeData")
            val castList = animeData.characters.take(3).map { entry ->
                "${entry.character.name} (VA: ${entry.voice_actors.firstOrNull()?.name ?: "N/A"})"
            }
            binding.castText.text = "Main Cast: ${castList.joinToString("\n")}"
        }
    }

    override fun onStop() {
        super.onStop()
        if(::player.isInitialized){
            player.release()
        }
    }
}