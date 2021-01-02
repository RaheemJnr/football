package com.example.football.data.roomDatabase

import com.example.football.domain.LeagueTableModel
import com.example.football.domain.NewsModel

//convert db object in network object
fun List<NewsDatabase>.asNewsDomainModel(): List<NewsModel> {
    return map {
        NewsModel(
            title = it.title,
            description = it.description,
            urlToImage = it.urlToImage,
        )
    }
}

//convert db object in network object
fun List<LeagueTableDatabase>.asLeagueDomainModel(): List<LeagueTableModel> {
    return map {
        LeagueTableModel(
            name = it.name,
            total = it.total,
            played = it.win,
            win = it.win,
            draw = it.draw,
            loss = it.loss
        )
    }

}//convert db object in network object

//fun List<LiveScoreContainer>.asLiveScoreDomainModel(): List<NetworkLiveScore> {
//    return map {
//        NetworkLiveScore(
//            status = it.status,
//            team_1 = it.team_1,
//            team_2 = it.team_2,
//            score = it.score
//        )
//    }
//}

