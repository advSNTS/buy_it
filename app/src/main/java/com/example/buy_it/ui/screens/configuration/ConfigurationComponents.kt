package com.example.buy_it.ui.screens.configuration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.R
import com.example.buy_it.ui.components.TextInput

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
){
    var search by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 5.dp)
            .height(55.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.navbar),
            contentDescription = stringResource(R.string.navbar_background),
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.search),
                contentDescription = stringResource(R.string.home_icon),
                modifier = Modifier.size(28.dp)
            )
            TextInput(
                placeholder = "Buscar",
                item = search,
                onItemChange = {search = it}
            )
        }
    }
}

@Composable
@Preview
fun SearchBarPreview(){
    SearchBar()
}

@Composable
fun SubsectionText(
    text: String,
    modifier : Modifier = Modifier
){
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(860),
            color = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
@Preview
fun SubsectionTextPreview(){
    SubsectionText("Sapo")
}

@Composable
fun SubsectionItem(
    icon: ImageVector,
    text: String,
    secondText: String = "",
    modifier : Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.inicio),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(30.dp).padding(end = 8.dp)
        )
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(860),
                color = MaterialTheme.colorScheme.primary
            ),
        )
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = secondText,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(860),
                color = MaterialTheme.colorScheme.primary
            )
        )
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = stringResource(R.string.inicio),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(22.dp).graphicsLayer{scaleX=-1f}
        )
    }

}

@Composable
@Preview
fun SubsectionItemPreview(){
    SubsectionItem(Icons.Default.Lock,"Privacidad de la cuenta", "asass", Modifier)
}