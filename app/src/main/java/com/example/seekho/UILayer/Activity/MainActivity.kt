package com.example.seekho.UILayer.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seekho.DataLayer.RepositoryImpl.AnimeRepositoryImpl
import com.example.seekho.DataLayer.RetrofitInstance.RetrofitInstance
import com.example.seekho.DomainLayer.ViewModel.AnimeListViewModel
import com.example.seekho.UILayer.Adapter.AnimeSeriesListAdapter
import com.example.seekho.UILayer.Factory.AnimeListViewModelFactory
import com.example.seekho.databinding.ActivityAnimeMainPageBinding
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    private lateinit var binding : ActivityAnimeMainPageBinding
    private lateinit var viewModel : AnimeListViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.animeList
        val repository = AnimeRepositoryImpl(RetrofitInstance.api)
        val factory = AnimeListViewModelFactory(repository)
        viewModel = ViewModelProvider.create(this, factory)[AnimeListViewModel::class.java]
        val gson = Gson()
        val adapter = AnimeSeriesListAdapter(mutableListOf()){ anime ->
            val animeGson = gson.toJson(anime)
            val intent = Intent(this, AnimeDetailsActivity::class.java).apply {
                putExtra("anime", animeGson)
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        viewModel.fetchTopAnimeSeries()

        viewModel.getTopAnimeList.observe(this){ animeList ->
            Log.d("MainActivity", "$animeList")
            adapter.updateList(animeList)
        }

    }
}