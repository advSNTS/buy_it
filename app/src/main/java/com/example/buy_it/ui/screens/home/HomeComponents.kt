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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.sp
import com.example.buy_it.data.ReviewInfo

@Composable
fun ReviewCardUser(
    name: String,
    @DrawableRes imagen: Int = R.drawable.predet,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imagen),
            contentDescription = "Perfil",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(18.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
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
                .size(200.dp),
            contentScale = ContentScale.Crop
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
            imageVector = Icons.Default.ThumbUp,
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
