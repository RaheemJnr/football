package com.example.football.ui.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.football.ui.table.compose.LeagueTableScreen


class TableFragment : Fragment() {

    private lateinit var viewModel: TableViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val activity = requireNotNull(this.activity)
        viewModel = ViewModelProvider(this, TableViewModel.TableFactory(activity.application))
            .get(TableViewModel::class.java)

        return ComposeView(requireContext()).apply {
            setContent {
                LeagueTableScreen(viewModel)
            }
        }
    }
}