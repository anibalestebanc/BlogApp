package cl.cencosud.blogapp.login.presentation

sealed class LoginUiState {
    object DefaultState : LoginUiState()
    object Loading : LoginUiState()
    object Success : LoginUiState()
    data class Error(val error: Throwable) : LoginUiState()
}