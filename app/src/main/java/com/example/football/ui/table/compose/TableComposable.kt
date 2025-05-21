package com.example.football.ui.table.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.football.domain.LeagueTableModel
import com.example.football.ui.table.TableViewModel

@Composable
fun LeagueTableScreen(viewModel: TableViewModel = viewModel()) {
    val tables by viewModel.databaseLeagueTable.observeAsState(emptyList())

    if (tables.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            androidx.compose.material.CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(tables) { table ->
                LeagueTableRow(table)
                Divider()
            }
        }
    }
}

@Composable
fun LeagueTableRow(item: LeagueTableModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.name, style = MaterialTheme.typography.body1)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "${item.played}")
            Text(text = "${item.win}")
            Text(text = "${item.draw}")
            Text(text = "${item.loss}")
            Text(text = "${item.total}")
        }
    }
}
