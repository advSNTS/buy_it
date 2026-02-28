package com.example.buy_it.ui.screens.revieweditor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buy_it.data.local.ProductProvider
import com.example.buy_it.ui.components.MainBackground

@Composable
fun ReviewEditor(
    productId: String = "",
    onBackPressed: () -> Unit,
    onNotificationClick: () -> Unit,
    onPublish: (ReviewDraft) -> Unit,
    modifier: Modifier = Modifier
) {
    val product = remember(productId) { ProductProvider.byId(productId) }
    if (product == null) return

    var likeChoice by remember { mutableStateOf(LikeChoice.None) } // Like / Dislike / None
    var opinion by remember { mutableStateOf("") }

    val canPublish = opinion.isNotBlank() && likeChoice != LikeChoice.None

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        MainBackground()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ReviewEditorTopBar(
                title = "Escribir reseña",
                onBackPressed = onBackPressed,
                onNotificationClick = onNotificationClick
            )

            ReviewEditorCard(
                username = "@tu",
                productName = product.name,
                productImage = product.image,
                likeChoice = likeChoice,
                onLikeChoiceChange = { likeChoice = it },
                opinion = opinion,
                onOpinionChange = { opinion = it },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 10.dp)
            )

            PublishReviewButton(
                enabled = canPublish,
                text = "Publicar reseña",
                onClick = {
                    onPublish(
                        ReviewDraft(
                            productId = productId,
                            likeChoice = likeChoice,
                            opinion = opinion.trim()
                        )
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 18.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ReviewEditorPreview(){
    ReviewEditor("cafe_110g",{},{},{})
}

data class ReviewDraft(
    val productId: String,
    val likeChoice: LikeChoice,
    val opinion: String
)

enum class LikeChoice { None, Like, Dislike }