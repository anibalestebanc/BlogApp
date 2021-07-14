package cl.cencosud.blogapp.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.cencosud.blogapp.login.domain.SignInUseCase
import cl.cencosud.blogapp.login.presentation.model.LoginUiState
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val signInUseCase: SignInUseCase) : ViewModel() {
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
