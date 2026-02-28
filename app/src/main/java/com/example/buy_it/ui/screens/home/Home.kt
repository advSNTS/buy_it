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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.data.local.ReviewProvider
import com.example.buy_it.ui.components.BarNav
import com.example.buy_it.ui.components.LogoMessage
import com.example.buy_it.ui.components.MainBackground
import com.example.buy_it.ui.theme.Buy_itTheme


@Composable
fun MainScreen(onOpenDetail: (String) -> Unit) { 
    val allreviews = ReviewProvider.feed

    Column() {        
        allreviews.forEach { review ->
            ReviewCard(
                reviewInfo = review,
                onClick = { onOpenDetail(review.productId) } 
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrMainScreen(){
    Buy_itTheme() {
        MainScreen({})
    }
}

@Composable
fun Home(
    onNotificationClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onTrendsClick: () -> Unit,
    onOpenDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    onAddReview: (String) -> Unit
) {
    val allreviews = ReviewProvider.feed

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MainBackground()
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // Lista scrolleable de cards
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = 8.dp,
                )
            ) {
                items(allreviews) { review ->
                    ReviewCard(
                        reviewInfo = review,
                        onClick = { onOpenDetail(review.productId) }
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun HomePrev(){
    Buy_itTheme() {
        Home({},{},{},{},{},onAddReview = {})
    }
}