package com.example.buy_it.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.local.ReviewProvider
import com.example.buy_it.ui.components.BarNav
import com.example.buy_it.ui.components.MainBackground

@Composable
fun Home(
    onNotificationClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val feed = remember { mutableStateListOf<ReviewInfo>().apply { addAll(ReviewProvider.feed) } }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        MainBackground()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 90.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            item {
                HomeHeader(onNotificationClick = onNotificationClick)
            }

            items(feed) { review ->
                FeedCard(
                    info = review,
                    onMoreClick = { /* TODO menú */ },
                    onLikeClick = { /* TODO */ },
                    onCommentClick = { /* TODO */ }
                )
            }
        }
        BarNav(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Lo envía abajo al centro
                .padding(start = 8.dp, end = 8.dp), // Le da un margen para que "flote" y se vean las esquinas redondeadas
            onHomeClick = onHomeClick,
            onProfileClick = onProfileClick
        )
    }
}

@Composable
@Preview
fun HomePreview(){
    Home(onNotificationClick = {}, onHomeClick = { }, onProfileClick = { })
}
