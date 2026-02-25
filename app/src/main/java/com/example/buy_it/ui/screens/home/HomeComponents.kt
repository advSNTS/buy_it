package com.example.buy_it.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buy_it.R
import com.example.buy_it.ui.theme.Buy_itTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.sp
import com.example.buy_it.data.ReviewInfo
import java.sql.Date
import java.time.LocalDate

@Composable
fun ReviewCardUser(
    name: String,
    @DrawableRes imagen: Int = R.drawable.predet,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imagen),
            contentDescription = "Perfil",
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrReviewCardUser(){
    Buy_itTheme() {
        ReviewCardUser(
            name = "Hola",
        )
    }
}

@Composable
fun ProductAndName(
    @DrawableRes imagen: Int = R.drawable.rey,
    product: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imagen),
            contentDescription = product,
            modifier = Modifier
                .size(100.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = product,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrProductAndName(){
    Buy_itTheme() {
        ProductAndName(
            product = "Jabon REY 300g"
        )
    }
}

@Composable
fun PostInfo(
    percentageLikes: Int,
    range: String,
    comments: Int,
    modifier: Modifier = Modifier
) {
    val contentColor = MaterialTheme.colorScheme.primary

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.ThumbUp,
            contentDescription = "Likes",
            tint = contentColor
        )
        Text(
            text = "$percentageLikes%",
            color = contentColor,
            style = MaterialTheme.typography.bodyMedium
        )

        VerticalDivider(
            modifier = Modifier.height(20.dp),
            color = contentColor
        )

        Text(
            text = range,
            color = contentColor,
            style = MaterialTheme.typography.bodyMedium
        )

        VerticalDivider(
            modifier = Modifier.height(20.dp),
            color = contentColor
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.Comment,
            contentDescription = "Comments",
            tint = contentColor
        )
        Text(
            text = "$comments",
            color = contentColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrPostInfo(){
    Buy_itTheme() {
        PostInfo(
            range= "$600-1000",
            percentageLikes = 89,
            comments = 26
        )
    }
}

@Composable
fun BodyReview(
    text: String,
    isLike: Boolean,
    modifier: Modifier = Modifier
){
    val backgroundIcon = if (isLike) Icons.Outlined.ThumbUp else Icons.Outlined.ThumbDown
    val iconRotation = if (isLike) -30f else 30f
    Box(
        modifier = modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = backgroundIcon,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .rotate(iconRotation),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrReviBody(){
    Buy_itTheme() {
        BodyReview(
            text = "Muy buen jabon xd",
            true
        )
    }
}

@Composable
fun ReviewCard(
    reviewInfo: ReviewInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 6.dp) // menos padding abajo
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProductAndName(
                    imagen = reviewInfo.imgProd,
                    product = reviewInfo.product,
                    modifier = Modifier.weight(0.38f)
                )

                Column(
                    modifier = Modifier
                        .weight(0.62f)
                        .padding(start = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top // cambiado a Top para subir el icono
                    ) {
                        ReviewCardUser(
                            name = reviewInfo.name,
                            imagen = reviewInfo.pfp
                        )
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Opciones",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp) // pequeño ajuste fino
                        )
                    }
                    BodyReview(
                        text = reviewInfo.review,
                        isLike = reviewInfo.like
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp)) // reducido de 6 a 2

            PostInfo(
                percentageLikes = reviewInfo.percentageLikes,
                range = reviewInfo.range,
                comments = reviewInfo.comments,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
fun CardPreview(){
    val reviewInfo = ReviewInfo(
        pfp = R.drawable.predet,
        imgProd = R.drawable.rey,
        name = "buy it.",
        review = "Buen jabón, con el me hago los rituales de purificación.",
        product = "Jabon REY 300g",
        like = true,
        percentageLikes = 88,
        date = LocalDate.now(),
        range = "$6000-$8000",
        comments = 787
    )
    Buy_itTheme() { ReviewCard(reviewInfo)}

}