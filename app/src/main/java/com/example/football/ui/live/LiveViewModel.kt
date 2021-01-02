package com.example.football.ui.live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.data.network.LiveScoreApiCall
import com.example.football.data.network.asLiveScoreDomainModel
import com.example.football.domain.NetworkLiveScore
import kotlinx.coroutines.launch

enum class NetworkState { LOADING, SUCCESS, FAILURE }

class LiveViewModel : ViewModel() {

    //encapsulating liveData
    private val _liveScore = MutableLiveData<List<NetworkLiveScore>>()
    val liveScore: LiveData<List<NetworkLiveScore>> = _liveScore

    // The internal MutableLiveData status that stores the status of the most recent request
    private val _status = MutableLiveData<NetworkState>()

    // The external immutable LiveData for the request status String
    val status: LiveData<NetworkState>
        get() = _status

    init {
        getLiveScores()
    }

    /**
     * send network request to get news
     */
    fun getLiveScores() {
        viewModelScope.launch {
            try {
                _status.value = NetworkState.LOADING
                val liveScore = LiveScoreApiCall.liveScoreService.getliveScore().await()
                _liveScore.value = liveScore.asLiveScoreDomainModel()
                _status.value = NetworkState.SUCCESS
            } catch (t: Throwable) {
                _status.value = NetworkState.FAILURE
                _liveScore.value = ArrayList()
            }

        }
    }

}