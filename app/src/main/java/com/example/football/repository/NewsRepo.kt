package com.example.football.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.football.data.network.NewsApiCall
import com.example.football.data.network.asNewsDataBaseModel
import com.example.football.data.roomDatabase.FootballDatabase
import com.example.football.data.roomDatabase.asNewsDomainModel
import com.example.football.domain.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepo(private val newsdatabase: FootballDatabase) {


    //
    val newsFlow: Flow<List<NewsModel>> =
        newsdatabase.footballDAO.getNews().map { it.asNewsDomainModel() }


    //
    suspend fun refreshNews() {
        withContext(Dispatchers.IO) {
            try {
                val news = NewsApiCall.newsService.getNews().await()
                newsdatabase.footballDAO.insertAllNews(*news.asNewsDataBaseModel())
            } catch (t: Throwable) {
                Log.e("network error", "${t.message}")
            }
        }
    }
}