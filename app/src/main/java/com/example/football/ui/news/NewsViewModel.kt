package com.example.football.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.football.data.roomDatabase.FootballDatabase
import com.example.football.repository.NewsRepo
import kotlinx.coroutines.launch

/// class the set network status
enum class NewsNetworkState { LOADING, SUCCESS, FAILURE }

class NewsViewModel(app: Application) : AndroidViewModel(app) {

    //
    private val database = FootballDatabase.getInstance(app)
    private val repo = NewsRepo(database)


    init {
        getRNews()
    }

    /**
     * send network request to get news
     */
    fun getRNews() {
        viewModelScope.launch {
            repo.refreshNews()
        }
    }

    val news = repo.getNewsInDatabase

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class NewsFactory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewsViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}