package com.example.football.ui.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.repository.LeagueTableRepo
import com.example.football.ui.live.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TableViewModel(
    private val repo: LeagueTableRepo
) : ViewModel() {

    private val _status = MutableStateFlow(NetworkState.SUCCESS)
    val status: StateFlow<NetworkState> = _status

    init {
        getPremierLeagueTable()
    }

    private fun getPremierLeagueTable() {
        viewModelScope.launch {
            repo.refreshLeagueTable()
        }
    }

    val leagueTable = repo.leagueTableFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )
}
