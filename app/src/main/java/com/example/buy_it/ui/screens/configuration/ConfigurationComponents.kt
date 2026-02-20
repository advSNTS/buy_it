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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .padding(horizontal = 15.dp, vertical = 10.dp)
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
        )
    )
}

@Composable
@Preview
fun SubsectionTextPreview(){
    SubsectionText("Sapo")
}