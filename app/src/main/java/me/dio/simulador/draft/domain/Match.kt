package me.dio.simulador.draft.domain

import com.google.gson.annotations.SerializedName

data class Match (
    @SerializedName("descricao")
    val description: String,
    @SerializedName("mandante")
    val homeTeam: Team,
    @SerializedName("visitante")
    val awayTeam: Team,
    @SerializedName("local")
    val place: Place
)