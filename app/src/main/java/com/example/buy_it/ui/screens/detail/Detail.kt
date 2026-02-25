package com.example.buy_it.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.local.ProductProvider
import com.example.buy_it.data.local.ReviewProvider
import com.example.buy_it.ui.components.MainBackground

@Composable
fun Detail(
    productId: String,
    onBackPressed: () -> Unit,
    onNotificationClick: () -> Unit,
    onOpenComments: () -> Unit,
    modifier: Modifier = Modifier
) {
    val product = ProductProvider.byId(productId) ?: return

    val reviews = remember {
        mutableStateListOf<ReviewInfo>().apply {
            addAll(ReviewProvider.feed.filter { it.product == product.name })
            if (isEmpty()) addAll(ReviewProvider.feed)
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        MainBackground()

        Column(modifier = Modifier.fillMaxSize()) {
            DetailTopBar(
                title = "Ver detalle de producto",
                onBackPressed = onBackPressed,
                onNotificationClick = onNotificationClick
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 86.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
            ) {
                item {
                    ProductHeaderCard(
                        name = product.name,
                        imageRes = product.image,
                        range = product.range,
                        likePercent = product.likePercent,
                        ratingsCount = product.ratingsCount
                    )
                }

                item {
                    SectionTitle(text = "Opiniones del producto")
                }

                items(reviews) { review ->
                    ReviewMiniCard(
                        info = review,
                        onCommentClick = onOpenComments
                    )
                }
            }
        }

        ReviewInputBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            text = "Reseñar...",
            onSend = { /* TODO: guardar reseña */ },
            onLike = { /* TODO */ }
        )
    }
}