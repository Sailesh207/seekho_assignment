package com.example.seekho.DataLayer.DataClass

import com.google.gson.annotations.SerializedName

data class TopAnimeSeries(
    @SerializedName("data")
    val animeList : List<AnimeDto>
)

data class AnimeDto(
    @SerializedName("mal_id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("episodes")
    val episodes: Int?,
    @SerializedName("score")
    val score: Double?,
    @SerializedName("images")
    val images: Images
)

data class Images (
    @SerializedName("jpg")
    val jpg : ImageUrl
)

data class ImageUrl(
    @SerializedName("image_url")
    val image_url : String
)

data class Anime(
    val mal_id: Int,
    val title: String,
    val episodes: Int,
    val rating: Double,
    val imageUrl: String
)