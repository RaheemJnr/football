package com.example.football.data.network


import com.example.football.data.roomDatabase.LeagueTableDatabase
import com.example.football.data.roomDatabase.NewsDatabase
import com.example.football.domain.NetworkLiveScore

//table container
fun TableContainer.asLeagueDataBaseModel(): Array<LeagueTableDatabase> {
    return table.map {
        LeagueTableDatabase(
            name = it.name,
            total = it.total,
            played = it.played,
            win = it.win,
            draw = it.draw,
            loss = it.loss
        )
    }.toTypedArray()


}

//news container
fun NewsContainer.asNewsDataBaseModel(): Array<NewsDatabase> {
    return articles.map {
        NewsDatabase(
            title = it.title,
            description = it.description,
            urlToImage = it.urlToImage
        )
    }.toTypedArray()
}

//liveScore container
fun LiveScoreContainer.asLiveScoreDomainModel(): List<NetworkLiveScore> {
    return data.map {
        NetworkLiveScore(
            status = it.status,
            team_2 = it.team_2,
            team_1 = it.team_1,
            score = it.score
        )
    }
}