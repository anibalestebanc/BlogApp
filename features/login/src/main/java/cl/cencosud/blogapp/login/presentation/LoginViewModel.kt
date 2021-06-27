package cl.cencosud.blogapp.login.presentation

import androidx.lifecycle.*
import cl.cencosud.blogapp.login.domain.LoginRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginState = MutableLiveData<LoginUiState>(LoginUiState.DefaultState)
    val loginState: LiveData<LoginUiState> = _loginState


    fun signIn(email: String, password: String) = viewModelScope.launch {
        _loginState.value = LoginUiState.Loading
        try {
            loginRepository.signIn(email, password)
            _loginState.value = LoginUiState.Success
        } catch (error: Exception) {
            _loginState.value = LoginUiState.Error(error)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class LoginModelFactory(private val loginRepository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginRepository) as T
        }
        throw IllegalArgumentException("view model not found")
    }
}