package com.example.football.ui.news.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.football.domain.NewsModel
import com.example.football.ui.news.NewsViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel = viewModel()) {
    val news by viewModel.news.observeAsState(emptyList())
    if (news.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            androidx.compose.material.CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(news) { article ->
                NewsItem(article)
                Divider()
            }
        }
    }
}

@Composable
fun NewsItem(item: NewsModel) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column {
            item.urlToImage?.let { url ->
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = item.title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}
