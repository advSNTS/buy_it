package com.example.buy_it.ui.screens.prices

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.buy_it.R
import com.example.buy_it.data.PricedItems
import com.example.buy_it.ui.components.GradientMessage

@Composable
fun PriceCard(
    info: PricedItems,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(18.dp)
    val isUp = info.percentage >= 0

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
                Text("Precio reportado: $${info.price}", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)


            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val pillColor = if (isUp) MaterialTheme.colorScheme.tertiaryContainer
                else MaterialTheme.colorScheme.errorContainer

                val pillTextColor = if (isUp) MaterialTheme.colorScheme.onTertiaryContainer
                else MaterialTheme.colorScheme.onErrorContainer
                GradientMessage("Oferta: ", fontSize = 18.sp)
                Box(
                    modifier = Modifier
                        .background(pillColor, RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (isUp) "↑ +${info.percentage}%" else "↓ ${info.percentage}%",
                        color = pillTextColor,
                        fontWeight = FontWeight(700)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PriceCardPreview() {
    PriceCard(
        info = PricedItems(
            name = "carulla",
            price = 40.5,
            percentage = 10,
            image = R.drawable.carulla
        )
    ){}
}