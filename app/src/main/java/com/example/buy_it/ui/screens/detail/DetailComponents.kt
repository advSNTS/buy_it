package com.example.buy_it.ui.screens.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.data.ReviewInfo

@Composable
fun DetailTopBar(
    title: String,
    onBackPressed: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(26.dp)
                    .clickable(onClick = onBackPressed),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.outline,
                fontSize = 14.sp
            )
        }

        Icon(
            imageVector = Icons.Default.NotificationsNone,
            contentDescription = "Notifications",
            modifier = Modifier
                .size(26.dp)
                .clickable(onClick = onNotificationClick),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ProductHeaderCard(
    name: String,
    @DrawableRes imageRes: Int,
    range: String,
    likePercent: Int,
    ratingsCount: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(18.dp))
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = name,
                fontSize = 22.sp,
                fontWeight = FontWeight(800),
                color = MaterialTheme.colorScheme.onSurface
            )

            Image(
                painter = painterResource(imageRes),
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(14.dp)),
                contentScale = ContentScale.Crop
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "$range | Ver Tiendas",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight(600)
                )
                Text(
                    text = "➜",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp
                )
            }

            Text(
                text = "Opiniones del producto",
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight(700)
            )

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "Like",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "$likePercent%",
                    fontSize = 34.sp,
                    fontWeight = FontWeight(900),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "$ratingsCount calificaciones",
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
        }
    }
}

@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.padding(start = 18.dp, top = 6.dp),
        fontWeight = FontWeight(800),
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun ReviewMiniCard(
    info: ReviewInfo,
    onCommentClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(18.dp))
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(info.pfp),
                    contentDescription = "pfp",
                    modifier = Modifier.size(30.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = info.name,
                    fontWeight = FontWeight(800),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = info.review,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Ver comentarios (${info.comments})",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.clickable(onClick = onCommentClick)
                )
            }
        }
    }
}

@Composable
fun ReviewInputBar(
    text: String,
    onSend: () -> Unit,
    onLike: () -> Unit,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(10.dp, RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(24.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            placeholder = { Text(text) },
            modifier = Modifier.weight(1f),
            singleLine = true
        )
        Spacer(Modifier.width(10.dp))
        Icon(
            imageVector = Icons.Default.ThumbUp,
            contentDescription = "Like",
            modifier = Modifier
                .size(26.dp)
                .clickable(onClick = onLike),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}