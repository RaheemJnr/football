package com.example.football.di

import com.example.football.data.roomDatabase.FootballDatabase
import com.example.football.repository.LeagueTableRepo
import com.example.football.repository.NewsRepo
import com.example.football.ui.live.LiveViewModel
import com.example.football.ui.news.NewsViewModel
import com.example.football.ui.table.TableViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FootballDatabase.getInstance(androidContext()) }
    single { LeagueTableRepo(get()) }
    single { NewsRepo(get()) }

    viewModel { TableViewModel(get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { LiveViewModel() }
}
