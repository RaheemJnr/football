package com.example.football.data.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface footballDAO {
    /**
    league table dao to access the roomDb
    we just query to get all object from league table and to insert
    into league table database
     */
    @Query("SELECT * FROM leagueTable")
    fun getLeagueTable(): LiveData<List<LeagueTableDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLeagues(vararg videos: LeagueTableDatabase)


    /**
    news dao to access the roomDb
    we just query to get all object from news table and to insert
    into news table database
     */
    @Query("SELECT * FROM newsTable")
    fun getNews(): LiveData<List<NewsDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(vararg videos: NewsDatabase)


}