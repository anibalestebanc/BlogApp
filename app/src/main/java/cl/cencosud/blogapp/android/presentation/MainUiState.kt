package cl.cencosud.blogapp.android.presentation

sealed class MainUiState {
    object DefaultState : MainUiState()
    object Loading :  MainUiState()
    data class Error(val error : Throwable) : MainUiState()
    object ConnectionError : MainUiState()
    object DisplayUpdate : MainUiState()
}