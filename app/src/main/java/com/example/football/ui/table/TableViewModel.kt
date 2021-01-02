package com.example.football.ui.table

import android.app.Application
import androidx.lifecycle.*
import com.example.football.data.roomDatabase.FootballDatabase
import com.example.football.repository.LeagueTableRepo
import com.example.football.ui.live.NetworkState
import kotlinx.coroutines.launch

class TableViewModel(app: Application) : ViewModel() {


    // The internal MutableLiveData status that stores the status of the most recent request
    private val _status = MutableLiveData<NetworkState>()

    // The external immutable LiveData for the request status String
    val status: LiveData<NetworkState>
        get() = _status

    //
    private val database = FootballDatabase.getInstance(app)
    private val repo = LeagueTableRepo(database)

    init {
        getPremierLeagueTable()
    }

    /**
     * send network request to get premier league standing
     */
    private fun getPremierLeagueTable() {
        viewModelScope.launch {
            repo.refreshLeagueTable()
        }
    }


    val databaseLeagueTable = repo.getLeagueTableInDatabase


    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class TableFactory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TableViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TableViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}