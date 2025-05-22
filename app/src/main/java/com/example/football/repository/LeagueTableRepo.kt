package com.example.football.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.football.data.network.TableApiCall
import com.example.football.data.network.asLeagueDataBaseModel
import com.example.football.data.roomDatabase.FootballDatabase
import com.example.football.data.roomDatabase.asLeagueDomainModel
import com.example.football.domain.LeagueTableModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LeagueTableRepo(private val leagueDatabase: FootballDatabase) {


    //
    val leagueTableFlow: Flow<List<LeagueTableModel>> =
        leagueDatabase.footballDAO.getLeagueTable().map { it.asLeagueDomainModel() }

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