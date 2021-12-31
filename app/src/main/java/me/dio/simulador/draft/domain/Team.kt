package me.dio.simulador.draft.domain

import com.google.gson.annotations.SerializedName

data class Team (
    @SerializedName("nome")
    val name: String,
    @SerializedName("estrelas")
    val stars: Int,
    @SerializedName("imagem")
    val image: String,
    var score: Int?
)
