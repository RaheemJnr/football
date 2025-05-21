package com.example.football.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.platform.ComposeView
import com.example.football.ui.news.compose.NewsScreen

class News : Fragment() {
    private lateinit var viewModel: NewsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val activity = requireNotNull(this.activity)
        viewModel = ViewModelProvider(this, NewsViewModel.NewsFactory(activity.application))
            .get(NewsViewModel::class.java)

        return ComposeView(requireContext()).apply {
            setContent {
                NewsScreen(viewModel)
            }
        }
    }
}