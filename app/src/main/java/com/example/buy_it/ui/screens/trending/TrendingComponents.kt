package com.example.buy_it.ui.screens.trending

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buy_it.ui.components.TextInput
import com.example.buy_it.ui.components.TextInputRounded

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
){
    var search by remember{ mutableStateOf("") }
    TextInputRounded(
        placeholder = "Buscar",
        modifier = modifier.padding(horizontal = 16.dp),
        item = search,
        onItemChange = {search = it}
    )
    }

@Composable
@Preview(showBackground = false)
fun SearchBarPreview(){
    SearchBar()
}