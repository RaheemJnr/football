package com.example.football.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.football.data.network.TableApiCall
import com.example.football.data.network.asLeagueDataBaseModel
import com.example.football.data.roomDatabase.FootballDatabase
import com.example.football.data.roomDatabase.asLeagueDomainModel
import com.example.football.domain.LeagueTableModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LeagueTableRepo(private val leagueDatabase: FootballDatabase) {


    //
    val getLeagueTableInDatabase: LiveData<List<LeagueTableModel>> = Transformations
        .map(leagueDatabase.footballDAO.getLeagueTable()) {
            it.asLeagueDomainModel()
        }

    //
    suspend fun refreshLeagueTable() {
        withContext(Dispatchers.IO) {
            try {
                val leagueTable = TableApiCall.tableService.getLeagueTable().await()
                leagueDatabase.footballDAO.insertAllLeagues(*leagueTable.asLeagueDataBaseModel())
            } catch (t: Throwable) {
                Log.e("network error", "${t.message}")
            }
        }
    }

}