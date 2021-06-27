package cl.cencosud.profile.presentation

sealed class ProfileUiState {
    object DefaultState : ProfileUiState()
    object Loading : ProfileUiState()
    object Success : ProfileUiState()
    data class Error(val error: Throwable) : ProfileUiState()
}