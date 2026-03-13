package com.example.buy_it.ui.screens.editinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buy_it.R
import com.example.buy_it.ui.components.FondoBlancoEditInfo
import com.example.buy_it.ui.components.MainButton
import com.example.buy_it.ui.components.PanelGlass
import com.example.buy_it.ui.components.PasswordInput
import com.example.buy_it.ui.components.TextInput
import com.example.buy_it.ui.theme.Buy_itTheme

@Composable
fun EditInfo(
    onSaveChanges: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditInfoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val icono = if (!uiState.mostrarPassword) R.drawable.hide else R.drawable.see

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FondoBlancoEditInfo()

        PanelGlass(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 18.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 34.dp, vertical = 36.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            PictureWithCircle()

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Editar perfil",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Actualiza tu información personal",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                FormFieldLabel(text = stringResource(R.string.nombre))
                TextInput(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(R.string.buy_it),
                    item = uiState.name,
                    onItemChange = { viewModel.onNameChange(it) }
                )

                FormFieldLabel(text = stringResource(R.string.email))
                TextInput(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(R.string.buyit_buyit_com),
                    item = uiState.email,
                    onItemChange = { viewModel.onEmailChange(it) }
                )

                FormFieldLabel(text = stringResource(R.string.contrasenna))
                PasswordInput(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(R.string.contrasenna),
                    item = uiState.password,
                    onItemChange = { viewModel.onPasswordChange(it) },
                    icono = icono,
                    mostrar = uiState.mostrarPassword,
                    onMostrarPassword = { viewModel.onToggleMostrarPassword() }
                )
            }

            Spacer(modifier = Modifier.height(34.dp))

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                text = stringResource(R.string.guardar_cambios),
                onClick = {
                    viewModel.onSaveChanges()
                    onSaveChanges()
                }
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
@Preview(showBackground = true)
fun EditInfoPreview() {
    Buy_itTheme {
        EditInfo(onSaveChanges = {})
    }
}