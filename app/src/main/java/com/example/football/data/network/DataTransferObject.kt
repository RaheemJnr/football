package com.example.football.data.network

import com.example.football.domain.Scores
import com.example.football.domain.TeamOne
import com.example.football.domain.TeamTwo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//table
@JsonClass(generateAdapter = true)
data class TableContainer(val data: TableData)

@JsonClass(generateAdapter = true)
data class TableData(val standings: List<NetworkStanding>)

@JsonClass(generateAdapter = true)
data class NetworkStanding(
    val team: TeamInfo,
    val stats: List<Stat>
)

@JsonClass(generateAdapter = true)
data class TeamInfo(val name: String)

@JsonClass(generateAdapter = true)
data class Stat(val name: String, val value: Int)


//news
@JsonClass(generateAdapter = true)
data class NewsContainer(@Json(name = "response") val articles: List<NetworkNews>)

@JsonClass(generateAdapter = true)
data class NetworkNews(
    val title: String,
    val competition: Competition?,
    @Json(name = "thumbnail") val urlToImage: String?,
)

@JsonClass(generateAdapter = true)
data class Competition(val name: String)


//livescore
@JsonClass(generateAdapter = true)
data class LiveScoreContainer(val events: List<NetworkLive>)

@JsonClass(generateAdapter = true)
data class NetworkLive(
    val status: Status,
    @Json(name = "awayTeam") val team_2: TeamTwo?,
    @Json(name = "homeTeam") val team_1: TeamOne?,
    @Json(name = "awayScore") val awayScore: CurrentScore?,
    @Json(name = "homeScore") val homeScore: CurrentScore?,
)

@JsonClass(generateAdapter = true)
data class Status(val description: String)

@JsonClass(generateAdapter = true)
data class CurrentScore(val current: Int)

