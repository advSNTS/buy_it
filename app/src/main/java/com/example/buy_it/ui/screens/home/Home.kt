package com.example.buy_it.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.local.ReviewProvider
import com.example.buy_it.ui.components.BarNav
import com.example.buy_it.ui.components.LogoMessage
import com.example.buy_it.ui.components.MainBackground
import com.example.buy_it.ui.theme.Buy_itTheme


@Composable
fun MainScreen(){
    val allreviews = ReviewProvider.feed

    Column() {
        allreviews.forEach {
            review -> ReviewCard(reviewInfo = review)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrMainScreen(){
    Buy_itTheme() {
        MainScreen()
    }
}

@Composable
fun Home() {
    val allreviews = ReviewProvider.feed

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MainBackground()
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header: logo + campana
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LogoMessage(tamano = 48.sp)

                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notificaciones",
                    modifier = Modifier.size(28.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            // Lista scrolleable de cards
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = 8.dp,
                    bottom = 90.dp // espacio para que la BarNav no tape las cards
                )
            ) {
                items(allreviews) { review ->
                    ReviewCard(reviewInfo = review)
                }
            }
        }

        // BarNav flotante abajo
        BarNav(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Preview
@Composable
fun HomePrev(){
    Buy_itTheme() {
        Home()

    }
}