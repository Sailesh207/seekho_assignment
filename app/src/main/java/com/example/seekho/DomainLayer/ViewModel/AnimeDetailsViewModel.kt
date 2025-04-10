package com.example.seekho.DomainLayer.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekho.DataLayer.DataClass.Anime
import com.example.seekho.DataLayer.DataClass.AnimeData
import com.example.seekho.DataLayer.DataClass.CharacterResponse
import com.example.seekho.DataLayer.RepositoryImpl.AnimeRepositoryImpl
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(private val repository: AnimeRepositoryImpl) : ViewModel() {
    private val animeDetails = MutableLiveData<AnimeData>()
    val getAnimeDetails : LiveData<AnimeData> = animeDetails

    private val characters = MutableLiveData<CharacterResponse>()
    val getCharacter : LiveData<CharacterResponse> = characters


    fun fetchAnimeData(id : Int) {
        viewModelScope.launch {
            animeDetails.postValue(repository.getAnimeDetails(id))
            characters.postValue(repository.getAnimeCharacter(id))
        }
    }



}