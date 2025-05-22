package com.example.football.ui.live.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel
import com.example.football.domain.NetworkLiveScore
import com.example.football.ui.live.LiveViewModel
import com.example.football.ui.live.NetworkState

@Composable
fun LiveScoreScreen(viewModel: LiveViewModel = koinViewModel()) {
    val scores by viewModel.liveScore.collectAsState()
    val status by viewModel.status.collectAsState()

    when (status) {
        NetworkState.LOADING -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            androidx.compose.material.CircularProgressIndicator()
        }
        NetworkState.FAILURE -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Failed to load")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { viewModel.getLiveScores() }) {
                    Text("Retry")
                }
            }
        }
        else -> LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(scores) { score ->
                LiveScoreItem(score)
                Divider()
            }
        }
    }
}

@Composable
fun LiveScoreItem(item: NetworkLiveScore) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(Modifier.padding(8.dp)) {
            Text(text = item.status, style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = item.team_1?.name ?: "")
                    Text(text = item.score.full_time.team_1)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = item.team_2?.name ?: "")
                    Text(text = item.score.full_time.team_2)
                }
            }
        }
    }
}
