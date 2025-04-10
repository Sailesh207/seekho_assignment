package com.example.seekho.DataLayer.ApiService

import com.example.seekho.DataLayer.DataClass.AnimeDetails
import com.example.seekho.DataLayer.DataClass.CharacterResponse
import com.example.seekho.DataLayer.DataClass.TopAnimeSeries
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface AnimeApiService{

    @GET("anime/{id}/full")
    suspend fun fetchAnimeDetails(
        @Path("id") id : Int
    ): Response<AnimeDetails>

    @GET("top/anime")
    suspend fun fetchTopAnimeMovies() : Response<TopAnimeSeries>

    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacter(
        @Path("id") id : Int
    ) : Response<CharacterResponse>
}