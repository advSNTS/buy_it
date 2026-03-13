package com.example.buy_it.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buy_it.R
import com.example.buy_it.ui.components.CheckAndText
import com.example.buy_it.ui.components.FondoBlancoRegister
import com.example.buy_it.ui.components.MainButton
import com.example.buy_it.ui.components.PanelGlass
import com.example.buy_it.ui.components.PasswordInput
import com.example.buy_it.ui.components.TextInput
import com.example.buy_it.ui.theme.Buy_itTheme

@Composable
fun Register(
    registerButtonPressed: () -> Unit,
    onBackScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.navigateToHome) {
        if (uiState.navigateToHome) {
            registerButtonPressed()
            viewModel.onNavigationHandled()
        }
    }

    LaunchedEffect(uiState.navigateBack) {
        if (uiState.navigateBack) {
            onBackScreen()
            viewModel.onNavigationHandled()
        }
    }

    val iconoPassword = if (!uiState.mostrarPassword) R.drawable.hide else R.drawable.see
    val iconoConfirmPassword = if (!uiState.mostrarConfirmPassword) R.drawable.hide else R.drawable.see

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FondoBlancoRegister()

        PanelGlass(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 18.dp)
        )

        Image(
            painter = painterResource(R.drawable.arrowleft),
            contentDescription = stringResource(R.string.volver),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 26.dp, top = 28.dp)
                .height(28.dp)
                .clickable { viewModel.onBackClicked() }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 34.dp, vertical = 34.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            Text(
                text = stringResource(R.string.crear_cuenta),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Regístrate para empezar a compartir opiniones",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(34.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                FormFieldLabel(text = stringResource(R.string.nombre_de_usuario))
                TextInput(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(R.string.nombre_de_usuario),
                    item = uiState.username,
                    onItemChange = { viewModel.onUsernameChange(it) }
                )

                FormFieldLabel(text = stringResource(R.string.email))
                TextInput(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(R.string.email),
                    item = uiState.email,
                    onItemChange = { viewModel.onEmailChange(it) }
                )

                FormFieldLabel(text = stringResource(R.string.contrasenna))
                PasswordInput(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(R.string.contrasenna),
                    item = uiState.password,
                    onItemChange = { viewModel.onPasswordChange(it) },
                    icono = iconoPassword,
                    mostrar = uiState.mostrarPassword,
                    onMostrarPassword = { viewModel.onToggleMostrarPassword() }
                )

                FormFieldLabel(text = "Confirmar contraseña")
                PasswordInput(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Confirmar contraseña",
                    item = uiState.confirmPassword,
                    onItemChange = { viewModel.onConfirmPasswordChange(it) },
                    icono = iconoConfirmPassword,
                    mostrar = uiState.mostrarConfirmPassword,
                    onMostrarPassword = { viewModel.onToggleMostrarConfirmPassword() }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            CheckAndText(
                estado = uiState.acceptedTerms,
                onEstadoChange = { viewModel.onAcceptedTermsChange() },
                modifier = Modifier.align(Alignment.Start)
            )

            if (uiState.mostrarMensaje && uiState.errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = uiState.errorMessage,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                text = stringResource(R.string.crear_cuenta),
                onClick = { viewModel.onRegister() }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun FormFieldLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
@Preview(showBackground = false)
fun RegisterPreview() {
    Buy_itTheme {
        Register(
            registerButtonPressed = {},
            onBackScreen = {}
        )
    }
}