package cl.cencosud.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.blogapp.core.Result
import kotlinx.coroutines.Dispatchers

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

class LoginModelFactory(private val loginRepository : LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginViewModel::class.java).newInstance(loginRepository)
    }
}