package com.example.football.domain


data class LeagueTableModel(
    val name: String,
    val total: Int,
    val played: Int,
    val win: Int,
    val draw: Int,
    val loss: Int,
)

data class NewsModel(
    val title: String,
    val description: String,
    val urlToImage: String?,
)


data class NetworkLiveScore(
    val status: String,
    val team_1: TeamOne?,
    val team_2: TeamTwo?,
    val score: Scores,
)

data class TeamOne(
    val name: String,
)

data class TeamTwo(
    val name: String,
)

data class Scores(
    val full_time: FullTime,

    )

data class FullTime(
    val team_1: String,
    val team_2: String,
)

