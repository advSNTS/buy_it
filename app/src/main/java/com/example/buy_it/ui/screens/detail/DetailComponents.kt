package com.example.buy_it.ui.screens.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.ui.components.ProfileAsyncImage
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.buy_it.R

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
            .padding(start = 8.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onBackPressed),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.outline,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Icon(
            imageVector = Icons.Default.NotificationsNone,
            contentDescription = "Notificaciones",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onNotificationClick),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ProductHeaderCard(
    name: String,
    imageRes: String,
    range: String,
    likePercent: Int,
    ratingsCount: Int,
    modifier: Modifier = Modifier,
    onClickArrow: () -> Unit = {},
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = name,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                AsyncImage(
                    contentDescription = "User image",
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageRes)
                        .crossfade(true)
                        .build(),
                    error = painterResource(id = R.drawable.user_image_icon),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(220.dp).clip(CircleShape)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$range | Ver Tiendas",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Ver tiendas",
                    modifier = Modifier.clickable(onClick = onClickArrow),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = "Opiniones del producto",
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.SemiBold
            )

            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "Likes",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(26.dp)
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "$likePercent%",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "$ratingsCount calificaciones",
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(bottom = 2.dp)
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
        modifier = modifier.padding(start = 4.dp, top = 2.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun ReviewMiniCard(
    info: ReviewInfo,
    onCommentClick: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ProfileAsyncImage(
                    profileLink = info.profileImage,
                    size = 30
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = info.name,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = info.review,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp,
                lineHeight = 24.sp
            )

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

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = { Text(text) },
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 52.dp),
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )

            Spacer(Modifier.width(10.dp))

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.18f))
                    .clickable(onClick = onLike),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "Like",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}