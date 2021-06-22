package cl.cencosud.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import cl.cencosud.register.domain.RegisterRepository
import com.example.blogapp.core.Result
import kotlinx.coroutines.Dispatchers

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

class RegisterModelFactory(private val repository: RegisterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RegisterViewModel::class.java).newInstance(repository)
    }
}