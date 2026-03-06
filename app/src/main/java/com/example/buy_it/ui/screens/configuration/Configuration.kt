package com.example.buy_it.ui.screens.configuration

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DataThresholding
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.NoAccounts
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buy_it.R

@Composable
fun Configuration(
    onBackPressed: () -> Unit,
    configurationViewModel: ConfigurationViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    val state by configurationViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
    ){
        Image(
            painter = painterResource(R.drawable.arrowleft),
            contentDescription = stringResource(R.string.volver),
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, bottom = 15.dp)
                .size(30.dp)
                .clickable(onClick = { onBackPressed() })
        )

        Text(
            text = stringResource(R.string.configuraciones),
            style = TextStyle(
                fontSize = 45.sp,
                fontWeight = FontWeight(510),
                brush = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.17f to colorResource(R.color.graybluebuyit),
                        1f to colorResource(R.color.navybluebuyit)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0.5299f*1000f, -0.848f*100f)
                ),
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)
        )
        
        SearchBar(
            value = state.searchQuery,
            onValueChange = { configurationViewModel.onSearchQueryChanged(it) }
        )

        SubsectionText(stringResource(R.string.quien_pude_ver_tus_rese_as), Modifier.padding(start = 20.dp, top = 35.dp))
        
        SubsectionItem(
            icon = Icons.Default.Lock,
            text = stringResource(R.string.privacidad_de_la_cuenta),
            secondText = if (state.isAccountPrivate) stringResource(R.string.privada) else "Pública",
            onClick = { configurationViewModel.toggleAccountPrivacy() }
        )
        
        SubsectionItem(
            icon = Icons.Default.NoAccounts, 
            text = stringResource(R.string.bloqueados),
            secondText = state.blockedCount.toString()
        )
        
        SubsectionText(stringResource(R.string.general), Modifier.padding(start = 20.dp, top = 35.dp))
        
        SubsectionItem(
            icon = Icons.Default.Language, 
            text = stringResource(R.string.idioma),
            secondText = state.currentLanguage
        )
        
        SubsectionItem(Icons.Default.NotificationsActive, stringResource(R.string.notificaciones))
        
        SubsectionItem(
            icon = Icons.Default.DarkMode, 
            text = stringResource(R.string.temas),
            secondText = state.currentTheme
        )
        
        SubsectionItem(Icons.Default.DataThresholding, stringResource(R.string.tiempo_en_pantalla))
        SubsectionItem(Icons.Default.Cloud, stringResource(R.string.uso_de_datos))
        SubsectionItem(Icons.Default.Storage, stringResource(R.string.almacenamiento))
    }
}

@Composable
@Preview(showBackground = true)
fun ConfigurationPreview(){
    Configuration(onBackPressed = {}, configurationViewModel = viewModel())
}
