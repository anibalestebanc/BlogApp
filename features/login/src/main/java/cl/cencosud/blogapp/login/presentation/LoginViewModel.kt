package cl.cencosud.blogapp.login.presentation

import androidx.lifecycle.*
import cl.cencosud.blogapp.login.domain.SignInUseCase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.launch

class LoginViewModel(private val signInUseCase: SignInUseCase) : ViewModel() {
    private val _loginState = MutableLiveData<LoginUiState>(LoginUiState.DefaultState)
    val loginState: LiveData<LoginUiState> = _loginState

    fun signIn(email: String, password: String) = viewModelScope.launch {
        _loginState.value = LoginUiState.Loading
        try {
            signInUseCase.invoke(email, password)
            _loginState.value = LoginUiState.Success
        } catch (error: Exception) {
            checkExceptionError(error)
        }
    }

    private fun checkExceptionError(error: Exception) = when (error) {
        is FirebaseAuthInvalidCredentialsException ->
            _loginState.value = LoginUiState.CredentialError
        else -> _loginState.value = LoginUiState.Error(error)
    }

}

@Suppress("UNCHECKED_CAST")
class LoginModelFactory(private val signInUseCase: SignInUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(signInUseCase) as T
        }
        throw IllegalArgumentException("view model not found")
    }
}