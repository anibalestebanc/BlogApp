package cl.cencosud.register.presentation

sealed class RegisterUiState {
    object DefaultState : RegisterUiState()
    object Loading : RegisterUiState()
    object Success : RegisterUiState()
    data class Error(val error: Throwable) : RegisterUiState()
}