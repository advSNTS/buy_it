package com.example.buy_it.ui.screens.login

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.buy_it.R
import com.example.buy_it.navigation.Screen
import com.example.buy_it.ui.components.CheckAndText
import com.example.buy_it.ui.components.FondoBlanco
import com.example.buy_it.ui.components.GradientMessage
import com.example.buy_it.ui.components.MainButton
import com.example.buy_it.ui.components.PasswordInput
import com.example.buy_it.ui.components.SecondaryButton
import com.example.buy_it.ui.components.TextInput

@Composable
fun Login(
    loginViewModel: LoginViewModel,
    onRegisterButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by loginViewModel.uiState.collectAsState()
    val icono = if (!state.isPasswordVisible) R.drawable.hide else R.drawable.see

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FondoBlanco()
        Box(
            modifier = Modifier
                .width(352.dp)
                .height(800.dp)
                .background(
                    color = colorResource(R.color.glasswhite),
                    shape = RoundedCornerShape(size = 25.dp)
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GradientMessage(text = "buy it.")
                TextInput(
                    placeholder = "Email", 
                    item = state.email, 
                    onItemChange = { loginViewModel.onEmailChanged(it) }
                )
                PasswordInput(
                    placeholder = "Contraseña", 
                    item = state.password, 
                    onItemChange = { loginViewModel.onPasswordChanged(it) }, 
                    mostrar = state.isPasswordVisible, 
                    onMostrarPassword = { loginViewModel.togglePasswordVisibility() }, 
                    icono = icono
                )
                CheckAndText(
                    estado = state.isRememberMeChecked, 
                    onEstadoChange = { loginViewModel.onRememberMeChanged() },
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = stringResource(R.string.olvido_su_contrasenna),
                    textDecoration = TextDecoration.Underline,
                )
                MainButton(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.iniciar_sesion),
                    onClick = {loginViewModel.loginButtonPressed()}
                )
                SecondaryButton(
                    modifier = Modifier
                        .padding(bottom = 40.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.crear_cuenta),
                    onClick = onRegisterButtonPressed
                )
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = stringResource(R.string.otras_formas_de_iniciar_sesion), 
                    fontWeight = FontWeight(510), 
                    fontSize = 16.sp
                )
                Row {
                    LoginOption(
                        Modifier.size(74.dp),
                        backgroundGlass = painterResource(R.drawable.elipse4), 
                        loginIcon = painterResource(R.drawable.googlewhite)
                    )
                    Spacer(Modifier.width(30.dp))
                    LoginOption(
                        Modifier.size(74.dp),
                        backgroundGlass = painterResource(R.drawable.elipse5), 
                        loginIcon = painterResource(R.drawable.apple)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    Login(viewModel(),{})
}
