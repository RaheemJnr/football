package com.example.football.data.network

import com.example.football.domain.Scores
import com.example.football.domain.TeamOne
import com.example.football.domain.TeamTwo
import com.squareup.moshi.JsonClass

//table
@JsonClass(generateAdapter = true)
data class TableContainer(val table: List<NetworkTable>)

@JsonClass(generateAdapter = true)
data class NetworkTable(
    val name: String,
    val total: Int,
    val played: Int,
    val win: Int,
    val draw: Int,
    val loss: Int,
)


//news
@JsonClass(generateAdapter = true)
data class NewsContainer(val articles: List<NetworkNews>)

@JsonClass(generateAdapter = true)
data class NetworkNews(
    val title: String,
    val description: String,
    val urlToImage: String?,
)


//livescore
@JsonClass(generateAdapter = true)
data class LiveScoreContainer(val data: List<NetworkLive>)

data class NetworkLive(
    val status: String,
    val team_2: TeamTwo,
    val team_1: TeamOne,
    val score: Scores,
)

