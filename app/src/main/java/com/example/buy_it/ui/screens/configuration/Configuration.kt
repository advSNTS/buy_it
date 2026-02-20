package com.example.buy_it.ui.screens.configuration

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DataThresholding
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.NoAccounts
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.buy_it.R

@Composable
fun Configuration(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
){
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
                    end = Offset(0.5299f*1000f, -0.848f*100f) //x es el seno y y el cosenop
                ),
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)
        )
        SearchBar()
        SubsectionText(stringResource(R.string.quien_pude_ver_tus_rese_as), Modifier.padding(start = 20.dp, top = 35.dp))
        SubsectionItem(Icons.Default.Lock,
            stringResource(R.string.privacidad_de_la_cuenta), stringResource(R.string.privada)
        )
        SubsectionItem(Icons.Default.NoAccounts, stringResource(R.string.bloqueados),
            stringResource(R.string._11)
        )
        SubsectionText(stringResource(R.string.general), Modifier.padding(start = 20.dp, top = 35.dp))
        SubsectionItem(Icons.Default.Language, stringResource(R.string.idioma),
            stringResource(R.string.espa_ol)
        )
        SubsectionItem(Icons.Default.NotificationsActive, stringResource(R.string.notificaciones))
        SubsectionItem(Icons.Default.DarkMode, stringResource(R.string.temas),
            stringResource(R.string.claro)
        )
        SubsectionItem(Icons.Default.DataThresholding, stringResource(R.string.tiempo_en_pantalla))
        SubsectionItem(Icons.Default.Cloud, stringResource(R.string.uso_de_datos))
        SubsectionItem(Icons.Default.Storage, stringResource(R.string.almacenamiento))

    }
}

@Composable
@Preview(showBackground = true)
fun ConfigurationPreview(
    modifier: Modifier = Modifier
){
    Configuration({})
}