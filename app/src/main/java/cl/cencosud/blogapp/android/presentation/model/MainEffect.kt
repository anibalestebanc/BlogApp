package cl.cencosud.blogapp.android.presentation.model

sealed class MainEffect {
    object GotoHome : MainEffect()
    object GoToLogin : MainEffect()
}