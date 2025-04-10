package com.example.seekho.UILayer.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seekho.DataLayer.RepositoryImpl.AnimeRepositoryImpl
import com.example.seekho.DomainLayer.ViewModel.AnimeListViewModel

class AnimeListViewModelFactory(private val repository : AnimeRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AnimeListViewModel::class.java)){
            return AnimeListViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }
}