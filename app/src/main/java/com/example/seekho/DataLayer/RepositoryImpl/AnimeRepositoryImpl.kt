package com.example.seekho.DataLayer.RepositoryImpl

import android.util.Log
import com.example.seekho.DataLayer.ApiService.AnimeApiService
import com.example.seekho.DataLayer.DataClass.Anime
import com.example.seekho.DataLayer.DataClass.AnimeData
import com.example.seekho.DataLayer.DataClass.CharacterResponse
import com.example.seekho.DataLayer.DataClass.TopAnimeSeries
import com.example.seekho.DataLayer.RetrofitInstance.RetrofitInstance

class AnimeRepositoryImpl(private val api : AnimeApiService) {

    suspend fun getAnimeDetails(id : Int) : AnimeData? {
        return try {
            val response = RetrofitInstance.api.fetchAnimeDetails(id)
            if (response.isSuccessful){
                Log.d("AnimeRepository", "{${response.body()}}")
                response.body()?.data
            }
            else{
                Log.e("AnimeRepository", "Api Call Failed: {${response.code()}}")
                null
            }
        }
        catch (e : Exception){
            Log.e("AnimeRepository", "Exception in Anime Details", e)
            null
        }
    }

    suspend fun getTopAnimeSeries() : List<Anime> {
        return try {
            val response = RetrofitInstance.api.fetchTopAnimeMovies()
            Log.d("AnimeRepository", "${response.body()}")
            if(response.isSuccessful){
                response.body()?.animeList?.map {
                    Anime(
                        mal_id = it.id,
                        title = it.title,
                        episodes = it.episodes ?: 0,
                        rating = it.score ?: 0.0,
                        imageUrl = it.images.jpg.image_url
                    )
                } ?: emptyList()
            } else emptyList()
        } catch (e: Exception){
            emptyList()
        }
    }

    suspend fun getAnimeCharacter(id : Int) : CharacterResponse? {
        return try {
            val response = RetrofitInstance.api.getAnimeCharacter(id)
            if (response.isSuccessful){
                response.body()
            }
            else{
                Log.e("AnimeRepository", "Api Call Failed: {${response.code()}}")
                null
            }
        }
        catch (e : Exception){
            Log.e("AnimeRepository", "Exception in Anime Details", e)
            null
        }
    }
}