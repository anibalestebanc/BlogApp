package cl.cencosud.blogapp.home.presentation.home

import cl.cencosud.blogapp.android.data.model.Post

sealed class HomeUiState {
    object DefaultState : HomeUiState()
    object Loading : HomeUiState()
    object EmptyList : HomeUiState()
    data class Success(val postList: List<Post>) : HomeUiState()
    data class Error(val error: Throwable) : HomeUiState()
}