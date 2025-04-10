package com.example.seekho.DataLayer.DataClass

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class AnimeDetails(
    @SerializedName("data") val data : AnimeData
)

data class AnimeData (
    @SerializedName("title") val title : String,
    @SerializedName("episodes") val episode : Int,
    @SerializedName("rating") val rating : String,
    @SerializedName("genres") val genreList: List<Genre>,
    @SerializedName("synonyms") val synopsis : String,
    @SerializedName("trailer") val trailer : Trailer?
)

data class Genre(
    val mal_id: Int,
    val name: String
)

data class Trailer(
    @SerializedName("youtube_id") val youtubeId: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("embed_url") val embedUrl: String?
)