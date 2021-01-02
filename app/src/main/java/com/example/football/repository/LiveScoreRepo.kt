//package com.example.football.repository
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.Transformations
//import com.example.football.data.network.LiveScoreApiCall
//import com.example.football.data.network.asLiveDatabaseModel
//import com.example.football.data.roomDatabase.FootballDatabase
//import com.example.football.data.roomDatabase.asLiveDomainModel
//import com.example.football.domain.NetworkLiveScore
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class LiveScoreRepo(private val livedatabase: FootballDatabase) {
//
//
//    //
//    val getLiveScoreInDatabase: LiveData<List<NetworkLiveScore>> = Transformations
//        .map(livedatabase.footballDAO.getLive()) {
//            it.asLiveDomainModel()
//        }
//
//
//    //
//    suspend fun refreshLiveScore() {
//        withContext(Dispatchers.IO) {
//            try {
//                val live = LiveScoreApiCall.liveScoreService.getliveScore().await()
//                livedatabase.footballDAO.insertAllLive(*live.asLiveDatabaseModel())
//            } catch (t: Throwable) {
//                Log.e("network error", "${t.message}")
//            }
//        }
//    }
//
//}