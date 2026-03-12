package com.example.buy_it.ui.screens.editinfo

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class EditInfoViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(EditInfoState())
    val uiState: StateFlow<EditInfoState> = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.update { it.copy(name = value) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onToggleMostrarPassword() {
        _uiState.update { it.copy(mostrarPassword = !it.mostrarPassword) }
    }

    fun onSaveChanges() {
        val state = _uiState.value
        Log.d("Guardar cambios", "nombre: ${state.name}, email: ${state.email}, contraseña: ${state.password}")
    }
}