package com.example.buy_it.ui.screens.comments

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.R
import com.example.buy_it.data.CommentInfo
import com.example.buy_it.data.local.CommentProvider
import com.example.buy_it.ui.components.BarNav
import com.example.buy_it.ui.components.MainBackground

@Composable
fun Comments(
    productId: String,
    onBackPressed: () -> Unit,
    onNotificationClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val comments = remember(productId) { CommentProvider.byProduct(productId) }
    val (text, setText) = remember { mutableStateOf("") }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        MainBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 86.dp)
        ) {
            CommentsTopBar(
                title = "Comentarios",
                onBackPressed = {
                    Log.d("Navigation", "Regresando desde Comentarios")
                    onBackPressed()
                },
                onNotificationClick = {
                    Log.d("Action", "Click en notificaciones desde Comentarios")
                    onNotificationClick()
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(comments) { c ->
                    CommentCard(info = c)
                }

                item {
                    CommentInputCard(
                        username = "@tu",
                        avatar = R.drawable.predet,
                        value = text,
                        onValueChange = setText,
                        onPublish = {
                            Log.d("FormSubmission", "Publicando comentario: '$text' para el producto ID: $productId")
                            /* TODO guardar comentario */
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }

        BarNav(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            onHomeClick = {
                Log.d("Navigation", "Navegando a Home desde Comentarios")
                onHomeClick()
            },
            onProfileClick = {
                Log.d("Navigation", "Navegando a Perfil desde Comentarios")
                onProfileClick()
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CommentsPreview(){
    Comments(
        productId = "1",
        onBackPressed = {},
        onNotificationClick = {},
        onHomeClick = {},
        onProfileClick = {}
    )
}

@Composable
private fun CommentsTopBar(
    title: String,
    onBackPressed: () -> Unit,
    onNotificationClick: () -> Unit
) {
    Row(
        modifier = Modifier
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
                fontSize = 36.sp,
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
private fun CommentCard(info: CommentInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
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
                        painter = painterResource(info.avatar),
                        contentDescription = "avatar",
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = info.username,
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

            Spacer(Modifier.height(10.dp))

            Text(
                text = info.text,
                fontSize = 22.sp,
                fontWeight = FontWeight(700),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.reloj),
                        contentDescription = "time",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = info.timeAgo,
                        color = MaterialTheme.colorScheme.outline
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "${info.likes}", color = MaterialTheme.colorScheme.outline)
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Likes",
                        tint = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}

@Composable
private fun CommentInputCard(
    username: String,
    avatar: Int,
    value: String,
    onValueChange: (String) -> Unit,
    onPublish: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(avatar),
                    contentDescription = "avatar",
                    modifier = Modifier.size(28.dp).clip(CircleShape)
                )
                Spacer(Modifier.width(10.dp))
                Text(text = username, fontWeight = FontWeight(700))
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = MaterialTheme.colorScheme.outline
                )
            }

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Escribe un comentario...") },
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        Log.d("Action", "Botón Publicar presionado por $username")
                        onPublish()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Publicar",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight(700)
                )
            }
        }
    }
}
