package me.dio.simulador.draft.domain

data class Match (
    val description: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val place: Place
)