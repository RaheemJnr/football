package com.example.football.data.network


import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * A retrofit service to fetch league table.
 */
interface GetTableService {
    @GET("lookuptable.php?l=4328&s=2020-2021")
    fun getLeagueTable(): Deferred<TableContainer>
}

/**
 * A retrofit service to fetch live score.
 */
interface GetLiveScoreService {
    @Headers(
        "x-rapidapi-key: c2e7e90134mshfc788d6b60b3aeep1de18ejsnd665ab39b49a",
        "x-rapidapi-host: livescore-football.p.rapidapi.com"
    )
    @GET("matches-by-league?country_code=england&league_code=premier-league&timezone_utc=0%3A00&round=17")
    fun getliveScore(): Deferred<LiveScoreContainer>
}

/**
 * A retrofit service to fetch News.
 */
interface GetNewsService {
    @GET("top-headlines?sports&apiKey=fcdbed254adc419592031f3fb4d86d72&country=gb")
    fun getNews(): Deferred<NewsContainer>
}
