package com.example.football.data.network


import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * A retrofit service to fetch league table.
 */
interface GetTableService {
    @GET("leagues/eng.1/standings?season=2023&sort=asc")
    fun getLeagueTable(): Deferred<TableContainer>
}

/**
 * A retrofit service to fetch live score.
 */
interface GetLiveScoreService {
    @GET("sport/football/events/live")
    fun getliveScore(): Deferred<LiveScoreContainer>
}

/**
 * A retrofit service to fetch News.
 */
interface GetNewsService {
    @GET("video-api/v3/")
    fun getNews(): Deferred<NewsContainer>
}
