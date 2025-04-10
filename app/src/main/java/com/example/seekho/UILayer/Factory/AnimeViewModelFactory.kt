package com.example.seekho.UILayer.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seekho.DataLayer.RepositoryImpl.AnimeRepositoryImpl
import com.example.seekho.DomainLayer.ViewModel.AnimeDetailsViewModel

class AnimeViewModelFactory(private val repository :AnimeRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AnimeDetailsViewModel::class.java)) {
            return AnimeDetailsViewModel(repository) as T
        }
        throw IllegalStateException("ViewModel Not Found")
    }
}