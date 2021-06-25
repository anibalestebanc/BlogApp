package cl.cencosud.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import cl.cencosud.register.domain.RegisterRepository
import cl.cencosud.blogapp.android.core.Result
import kotlinx.coroutines.Dispatchers
import java.lang.IllegalArgumentException

class RegisterViewModel(private val repository: RegisterRepository) : ViewModel() {

    fun signUp(email: String, password: String, username: String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
            emit(Result.Loading())
            try {
                emit(Result.Success(repository.signUp(email, password, username)))
            } catch (e: Exception) {
                emit(Result.Failure(e))
            }
        }
}

@Suppress("UNCHECKED_CAST")
class RegisterModelFactory(private val repository: RegisterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("view model not found")
    }
}