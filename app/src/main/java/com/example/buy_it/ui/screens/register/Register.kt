package com.example.buy_it.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.R
import com.example.buy_it.ui.components.CheckAndText
import com.example.buy_it.ui.components.FondoBlancoRegister
import com.example.buy_it.ui.components.MainButton
import com.example.buy_it.ui.components.PanelGlass
import com.example.buy_it.ui.components.TextInput

@Composable
fun Register(
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FondoBlancoRegister()
        PanelGlass()
        Image(
            painter = painterResource(R.drawable.arrowleft),
            contentDescription = stringResource(R.string.volver),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 30.dp)
                .offset(y = 40.dp)
                .size(35.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 100.dp)
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        ) {

            Text(
                modifier = Modifier.padding(5.dp),
                text = stringResource(R.string.crear_cuenta),
                fontSize = 46.sp,
                fontWeight = FontWeight(510),
                color = colorResource(R.color.navybluebuyit),
                textAlign = TextAlign.Center,

            )
            Spacer(Modifier.height(70.dp))
            TextInput(
                text= stringResource(R.string.nombre_de_usuario)
            )
            TextInput(
                text = stringResource(R.string.email)
            )
            TextInput(
                text = stringResource(R.string.contrase_a)
            )
            TextInput(
                text = stringResource(R.string.confirmar_contrase_a)
            )
            Spacer(Modifier.height(20.dp))
            CheckAndText(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(20.dp))
            MainButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                text = stringResource(R.string.crear_cuenta)
            )

        }
    }

}

@Composable
@Preview(showBackground = false)
fun RegisterPreview(){
    Register()
}