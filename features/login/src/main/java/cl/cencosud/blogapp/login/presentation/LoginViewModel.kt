package cl.cencosud.blogapp.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import cl.cencosud.blogapp.login.domain.LoginRepository
import cl.cencosud.blogapp.android.core.Result
import kotlinx.coroutines.Dispatchers
import java.lang.IllegalArgumentException

class LoginViewModel(private val loginRepository : LoginRepository) : ViewModel() {

    fun signIn(email: String, password: String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Result.Loading())
        try {
            emit(Result.Success(loginRepository.signIn(email, password)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
}

@Suppress("UNCHECKED_CAST")
class LoginModelFactory(private val loginRepository : LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(loginRepository ) as T
        }
        throw IllegalArgumentException("view model not found")
    }
}