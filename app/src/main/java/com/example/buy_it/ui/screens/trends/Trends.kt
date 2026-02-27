package com.example.buy_it.ui.screens.trends

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.buy_it.data.TrendInfo
import com.example.buy_it.data.local.TrendProvider
import com.example.buy_it.ui.components.BarNav
import com.example.buy_it.ui.components.MainBackground
import com.example.buy_it.R
import com.example.buy_it.ui.theme.Buy_itTheme

@Composable
fun Trends(
    onNotificationClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onTrendsClick: () -> Unit,
    onOpenDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    val trends = remember { TrendProvider.trends }

    val filtered = remember(query, trends) {
        if (query.isBlank()) trends
        else trends.filter { it.name.contains(query, ignoreCase = true) }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        MainBackground()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                TrendsHeader(onNotificationClick = onNotificationClick)
            }

            item {
                SearchBar(
                    value = query,
                    onValueChange = { query = it }
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SortButton()
                    FilterButton()
                }
            }

            item {
                Text(
                    text = "Tendencias",
                    fontSize = 34.sp,
                    fontWeight = FontWeight(800),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Calificaciones", color = MaterialTheme.colorScheme.outline)
                    Text("Precio", color = MaterialTheme.colorScheme.outline)
                }
            }

            items(filtered) { item ->
                TrendCard(
                    info = item,
                    onClick = { onOpenDetail(item.id) }
                )
            }
        }

        BarNav(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            onHomeClick = onHomeClick,
            onProfileClick = onProfileClick,
            onBuscarClick = onTrendsClick
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TrendsPreview(){
    Buy_itTheme {
        Trends({},{},{},{},{})
    }
}

@Composable
private fun TrendsHeader(
    onNotificationClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "buy it.",
            fontSize = 34.sp,
            fontWeight = FontWeight(700),
            color = MaterialTheme.colorScheme.primary
        )

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
private fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Buscar") },
        singleLine = true,
        trailingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
        shape = RoundedCornerShape(22.dp)
    )
}

@Composable
private fun SortButton() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { /* TODO ordenar */ }
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Text("Ordenar por:", color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(8.dp))
        Icon(Icons.Default.ArrowForward, contentDescription = "Ordenar")
    }
}

@Composable
private fun FilterButton() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { /* TODO filtros */ }
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Icon(Icons.Default.Tune, contentDescription = "Filtros")
        Spacer(Modifier.width(8.dp))
        Text("Filtros")
    }
}

@Composable
private fun TrendCard(
    info: TrendInfo,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(18.dp)
    val isUp = info.deltaPercent >= 0

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, shape)
            .background(MaterialTheme.colorScheme.onPrimary, shape)
            .clickable(onClick = onClick)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Image(
            painter = painterResource(info.image),
            contentDescription = info.name,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = info.name,
                fontWeight = FontWeight(700),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("👍 ${info.ratingPercent}%", color = MaterialTheme.colorScheme.outline)

                val pillColor = if (isUp) MaterialTheme.colorScheme.tertiaryContainer
                else MaterialTheme.colorScheme.errorContainer

                val pillTextColor = if (isUp) MaterialTheme.colorScheme.onTertiaryContainer
                else MaterialTheme.colorScheme.onErrorContainer

                Box(
                    modifier = Modifier
                        .background(pillColor, RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (isUp) "↑ +${info.deltaPercent}%" else "↓ ${info.deltaPercent}%",
                        color = pillTextColor,
                        fontWeight = FontWeight(700)
                    )
                }
            }
        }
    }
}
