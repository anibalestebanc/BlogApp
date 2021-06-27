package cl.cencosud.blogapp.home.presentation.camera

sealed class CameraUiState {
    object DefaultState : CameraUiState()
    object Loading : CameraUiState()
    object Success : CameraUiState()
    data class Error(val error: Throwable) : CameraUiState()
}