package com.example.football.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.repository.NewsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class NewsNetworkState { LOADING, SUCCESS, FAILURE }

class NewsViewModel(
    private val repo: NewsRepo
) : ViewModel() {

    private val _status = MutableStateFlow(NewsNetworkState.SUCCESS)
    val status: StateFlow<NewsNetworkState> = _status

    init {
        getRNews()
    }

    fun getRNews() {
        viewModelScope.launch {
            repo.refreshNews()
        }
    }

    val news = repo.newsFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )
}
