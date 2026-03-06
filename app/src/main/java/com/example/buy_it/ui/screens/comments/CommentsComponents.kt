package com.example.buy_it.ui.screens.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.data.CommentItem
import com.example.buy_it.data.local.CommentsProvider

@Composable
fun CommentCard(
    info: CommentItem,
    onMoreClick: () -> Unit,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(18.dp))
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Image(
                        painter = painterResource(info.pfp),
                        contentDescription = "pfp",
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Column {
                        Text(
                            text = info.name,
                            fontWeight = FontWeight(700),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = info.username,
                            color = MaterialTheme.colorScheme.outline,
                            fontSize = 12.sp
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Más",
                    modifier = Modifier
                        .size(22.dp)
                        .clickable(onClick = onMoreClick),
                    tint = MaterialTheme.colorScheme.outline
                )
            }
            Text(
                text = info.comment,
                modifier = Modifier.padding(start = 38.dp),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp
            )

            CommentActions(
                time = info.time,
                likes = info.likes,
                onLikeClick = onLikeClick,
                modifier = Modifier.padding(start = 38.dp)
            )
        }
    }
}

@Composable
fun CommentActions(
    time: Int,
    likes: Int,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.Timer,
            contentDescription = "Time",
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Hace $time min",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = likes.toString(),
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.size(4.dp))
        Icon(
            imageVector = Icons.Default.ThumbUp,
            contentDescription = "Likes",
            modifier = Modifier
                .size(20.dp)
                .clickable(onClick = onLikeClick)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommentCardPreview() {
    val sampleComment = CommentsProvider.CommentItems.first()
    CommentCard(
        info = sampleComment,
        onMoreClick = {},
        onLikeClick = {}
    )
}
