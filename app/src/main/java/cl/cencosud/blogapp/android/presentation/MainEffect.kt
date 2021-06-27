package cl.cencosud.blogapp.android.presentation

sealed class MainEffect {
    object GotoHome : MainEffect()
    object GoToLogin : MainEffect()
}