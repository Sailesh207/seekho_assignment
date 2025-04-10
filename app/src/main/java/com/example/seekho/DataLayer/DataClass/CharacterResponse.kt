package com.example.seekho.DataLayer.DataClass

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data") val characters : List<CharacterEntry>
)

data class CharacterEntry(
    val character: Character,
    val voice_actors: List<VoiceActor>
)

data class Character(
    val name: String
)

data class VoiceActor(
    val name: String,
    val language: String
)