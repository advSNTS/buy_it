package com.example.buy_it.ui.screens.revieweditor

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.R
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ReviewEditorTopBar(
    title: String,
    onBackPressed: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 10.dp, end = 16.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onBackPressed() }
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = title,
                fontSize = 34.sp,
                fontWeight = FontWeight(800),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Image(
            painter = painterResource(R.drawable.campana),
            contentDescription = "Notificaciones",
            modifier = Modifier
                .size(28.dp)
                .clickable(onClick = onNotificationClick)
        )
    }
}

@Composable
fun ReviewEditorCard(
    username: String,
    productName: String,
    @DrawableRes productImage: Int,
    likeChoice: LikeChoice,
    onLikeChoiceChange: (LikeChoice) -> Unit,
    opinion: String,
    onOpinionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(18.dp)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(8.dp, shape),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.predet),
                        contentDescription = "avatar",
                        modifier = Modifier.size(34.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = username,
                        fontWeight = FontWeight(700),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = MaterialTheme.colorScheme.outline
                )
            }

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(productImage),
                    contentDescription = productName,
                    modifier = Modifier
                        .size(140.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(14.dp))
                Text(
                    text = productName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(800),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChoiceButton(
                    selected = likeChoice == LikeChoice.Like,
                    icon = Icons.Outlined.ThumbUp,
                    label = "Me gusta",
                    onClick = { onLikeChoiceChange(LikeChoice.Like) }
                )
                ChoiceButton(
                    selected = likeChoice == LikeChoice.Dislike,
                    icon = Icons.Outlined.ThumbDown,
                    label = "No me gusta",
                    onClick = { onLikeChoiceChange(LikeChoice.Dislike) }
                )
            }

            Spacer(Modifier.height(18.dp))

            Text(
                text = "Escribe tu opinión...",
                fontWeight = FontWeight(800),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = opinion,
                onValueChange = onOpinionChange,
                modifier = Modifier.fillMaxWidth().heightIn(min = 120.dp),
                placeholder = { Text("Escribe tu opinión...") },
                shape = RoundedCornerShape(14.dp)
            )
        }
    }
}

@Composable
private fun ChoiceButton(
    selected: Boolean,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(14.dp)
    val bg =
        if (selected) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.background

    Column(
        modifier = modifier
            .clip(shape)
            .background(bg, shape)
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = label,
            fontWeight = FontWeight(700),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun PublishReviewButton(
    enabled: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(52.dp),
        shape = RoundedCornerShape(26.dp)
    ) {
        Text(text = text, fontWeight = FontWeight(800))
    }
}