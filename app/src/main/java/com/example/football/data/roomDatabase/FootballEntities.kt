package com.example.football.data.roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * So because we have different network calls to diff apis we need different database to store each
 * network response for offline caching
 */
@Entity(tableName = "leagueTable")
data class LeagueTableDatabase constructor(
    @PrimaryKey
    val name: String,
    val total: Int,
    val played: Int,
    val win: Int,
    val draw: Int,
    val loss: Int,
)


@Entity(tableName = "newsTable")
data class NewsDatabase(
    @PrimaryKey
    val title: String,
    val description: String,
    val urlToImage: String?,

    )

//@Entity(tableName = "liveTable")
//data class LIveDatabase(
//    @PrimaryKey
//    val status: String,
//    val team_1: TeamOne,
//    val team_2: TeamTwo,
//    val score: Scores,
//)