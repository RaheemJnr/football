package com.example.football.ui.live

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.data.network.LiveScoreApiCall
import com.example.football.data.network.asLiveScoreDomainModel
import com.example.football.domain.NetworkLiveScore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

enum class NetworkState { LOADING, SUCCESS, FAILURE }

class LiveViewModel : ViewModel() {

    private val _liveScore = MutableStateFlow<List<NetworkLiveScore>>(emptyList())
    val liveScore: StateFlow<List<NetworkLiveScore>> = _liveScore

    private val _status = MutableStateFlow(NetworkState.LOADING)
    val status: StateFlow<NetworkState> = _status

    init {
        getLiveScores()
    }

    fun getLiveScores() {
        viewModelScope.launch {
            try {
                _status.value = NetworkState.LOADING
                val liveScore = LiveScoreApiCall.liveScoreService.getliveScore().await()
                _liveScore.value = liveScore.asLiveScoreDomainModel()
                _status.value = NetworkState.SUCCESS
            } catch (t: Throwable) {
                _status.value = NetworkState.FAILURE
                _liveScore.value = emptyList()
            }
        }
    }
}
