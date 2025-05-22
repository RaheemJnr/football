package com.example.football.data.network


import com.example.football.data.roomDatabase.LeagueTableDatabase
import com.example.football.data.roomDatabase.NewsDatabase
import com.example.football.domain.NetworkLiveScore
import com.example.football.domain.Scores
import com.example.football.domain.FullTime

//table container
fun TableContainer.asLeagueDataBaseModel(): Array<LeagueTableDatabase> {
    return data.standings.map { standing ->
        val stats = standing.stats.associateBy { it.name }
        LeagueTableDatabase(
            name = standing.team.name,
            total = stats["points"]?.value ?: 0,
            played = stats["gamesPlayed"]?.value ?: 0,
            win = stats["wins"]?.value ?: 0,
            draw = stats["draws"]?.value ?: 0,
            loss = stats["losses"]?.value ?: 0
        )
    }.toTypedArray()


}

//news container
fun NewsContainer.asNewsDataBaseModel(): Array<NewsDatabase> {
    return articles.map {
        NewsDatabase(
            title = it.title,
            description = it.competition?.name ?: "",
            urlToImage = it.urlToImage
        )
    }.toTypedArray()
}

//liveScore container
fun LiveScoreContainer.asLiveScoreDomainModel(): List<NetworkLiveScore> {
    return events.map {
        NetworkLiveScore(
            status = it.status.description,
            team_2 = it.team_2,
            team_1 = it.team_1,
            score = Scores(
                full_time = FullTime(
                    team_1 = it.homeScore?.current?.toString() ?: "0",
                    team_2 = it.awayScore?.current?.toString() ?: "0"
                )
            )
        )
    }
}