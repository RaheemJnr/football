package com.example.football

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.football.domain.LeagueTableModel
import com.example.football.domain.NetworkLiveScore
import com.example.football.domain.NewsModel

//binding for team name
@BindingAdapter("teamName")
fun TextView.setTeamName(Item: LeagueTableModel?) {
    Item?.let {
        text = Item.name
    }
}

//binding for match played
@BindingAdapter("matchPlayed")
fun TextView.setMatchPlayed(Item: LeagueTableModel?) {
    Item?.let {
        text = Item.played.toString()
    }
}

//binding for match won
@BindingAdapter("matchWon")
fun TextView.setMatchWOn(Item: LeagueTableModel?) {
    Item?.let {
        text = Item.win.toString()
    }
}

//binding for match lost
@BindingAdapter("matchLost")
fun TextView.setMatchLost(Item: LeagueTableModel?) {
    Item?.let {
        text = Item.loss.toString()
    }
}

//binding for match draw
@BindingAdapter("matchDraw")
fun TextView.setMatchDraw(Item: LeagueTableModel?) {
    Item?.let {
        text = Item.draw.toString()
    }
}

//binding for points
@BindingAdapter("matchPoints")
fun TextView.setMatchPoints(Item: LeagueTableModel?) {
    Item?.let {
        text = Item.total.toString()
    }
}

/**
 * News Bindind adapter
 */
//binding for title
@BindingAdapter("newsTitle")
fun TextView.setNewsTitle(Item: NewsModel?) {
    Item?.let {
        text = Item.title
    }
}

//binding for description
@BindingAdapter("newsDescription")
fun TextView.setNewsDescription(Item: NewsModel?) {
    Item?.let {
        text = Item.description
    }
}
//binding for images
/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView?, url: String?) {
    if (imageView != null) {
        url?.let {
            val toImageUri = it.toUri().buildUpon().scheme("https").build()
            Glide.with(imageView.context)
                .load(toImageUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_connection_error)
                )
                .into(imageView)
        }
    }
}

/**
 * LiveScore Binding adapter
 */
//binding for teamTwo
@BindingAdapter("teamTwo")
fun TextView.setTeamTwo(Item: NetworkLiveScore?) {
    Item?.let {
        text = Item.team_2?.name
    }
}

//binding for teamOne
@BindingAdapter("teamOne")
fun TextView.setTeamOne(Item: NetworkLiveScore?) {
    Item?.let {
        text = Item.team_1?.name
    }
}

//binding for team2 score
@BindingAdapter("teamTwoScore")
fun TextView.setTeamTwoScore(Item: NetworkLiveScore?) {
    Item?.let {
        text = Item.score.full_time.team_2
    }
}

//binding for team1 score
@BindingAdapter("teamOneScore")
fun TextView.setTeamOneScore(Item: NetworkLiveScore?) {
    Item?.let {
        text = Item.score.full_time.team_1
    }
}

//binding for teamOne
@BindingAdapter("matchStatus")
fun TextView.setmatchStatus(Item: NetworkLiveScore?) {
    Item?.let {
        text = Item.status
    }
}