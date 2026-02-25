package com.example.buy_it.ui.screens.trending

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buy_it.data.local.ProfileItemsProvider
import com.example.buy_it.data.local.ReviewProvider.feed
import com.example.buy_it.data.local.TrendingItemsProvider
import com.example.buy_it.ui.components.BarNav
import com.example.buy_it.ui.components.MainBackground
import com.example.buy_it.ui.components.ProfilePost
//import com.example.buy_it.ui.screens.home.FeedCard
//import com.example.buy_it.ui.screens.home.HomeHeader

@Composable
fun Trending(
    onNotificationClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
){
    val allTrends = TrendingItemsProvider.itemsFromProfile
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        MainBackground()
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            SearchBar(Modifier.fillMaxWidth())
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 90.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
            ) {

            }
        }

    }

}

@Composable
@Preview(showBackground = true)
fun TrendingPreview(){
    Trending({},{},{})
}