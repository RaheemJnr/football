package com.example.football.ui.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.platform.ComposeView
import com.example.football.ui.live.compose.LiveScoreScreen


class LiveScore : Fragment() {

    private lateinit var viewModel: LiveViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = ViewModelProvider(this).get(LiveViewModel::class.java)
        return ComposeView(requireContext()).apply {
            setContent {
                LiveScoreScreen(viewModel)
            }
        }
    }
}