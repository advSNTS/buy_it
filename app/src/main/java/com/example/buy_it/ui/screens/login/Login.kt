package com.example.buy_it.ui.screens.login

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buy_it.R
import com.example.buy_it.ui.components.CheckAndText
import com.example.buy_it.ui.components.FondoBlanco
import com.example.buy_it.ui.components.GradientMessage
import com.example.buy_it.ui.components.MainButton
import com.example.buy_it.ui.components.PasswordInput
import com.example.buy_it.ui.components.SecondaryButton
import com.example.buy_it.ui.components.TextInput
import com.example.buy_it.ui.theme.Buy_itTheme

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
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .background(
                    color = colorResource(R.color.glasswhite),
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(horizontal = 24.dp, vertical = 28.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                GradientMessage(
                    text = "buy it.",
                    fontSize = 64.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Descubre, compara y comparte opiniones",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    TextInput(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "Email",
                        item = state.email,
                        onItemChange = { loginViewModel.onEmailChanged(it) }
                    )

                    PasswordInput(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "Contraseña",
                        item = state.password,
                        onItemChange = { loginViewModel.onPasswordChanged(it) },
                        mostrar = state.isPasswordVisible,
                        onMostrarPassword = { loginViewModel.togglePasswordVisibility() },
                        icono = icono
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CheckAndText(
                        estado = state.isRememberMeChecked,
                        onEstadoChange = { loginViewModel.onRememberMeChanged() }
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(R.string.olvido_su_contrasenna),
                        textDecoration = TextDecoration.Underline,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                if (state.mostrarMensaje && state.errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = state.errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                MainButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    text = stringResource(R.string.iniciar_sesion),
                    onClick = { loginViewModel.loginButtonPressed() }
                )

                Spacer(modifier = Modifier.height(12.dp))

                SecondaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    text = stringResource(R.string.crear_cuenta),
                    onClick = onRegisterButtonPressed
                )

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    Text(
                        text = "  Otras formas de iniciar sesión  ",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LoginOption(
                        modifier = Modifier.size(68.dp),
                        backgroundGlass = painterResource(R.drawable.elipse4),
                        loginIcon = painterResource(R.drawable.googlewhite)
                    )
                    LoginOption(
                        modifier = Modifier.size(68.dp),
                        backgroundGlass = painterResource(R.drawable.elipse5),
                        loginIcon = painterResource(R.drawable.apple)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    Buy_itTheme {
        Login(
            loginViewModel = viewModel(),
            onRegisterButtonPressed = {}
        )
    }
}