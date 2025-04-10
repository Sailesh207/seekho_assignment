package com.example.seekho.DomainLayer.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekho.DataLayer.DataClass.Anime
import com.example.seekho.DataLayer.RepositoryImpl.AnimeRepositoryImpl
import kotlinx.coroutines.launch


class AnimeListViewModel(private val repository: AnimeRepositoryImpl) : ViewModel() {
    private val topAnimeList = MutableLiveData<List<Anime>>()
    val getTopAnimeList : LiveData<List<Anime>> = topAnimeList

    fun fetchTopAnimeSeries() {
        viewModelScope.launch {
            topAnimeList.postValue(repository.getTopAnimeSeries())
        }
    }
}