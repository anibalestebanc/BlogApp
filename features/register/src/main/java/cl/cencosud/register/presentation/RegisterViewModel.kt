package cl.cencosud.register.presentation

import androidx.lifecycle.*
import cl.cencosud.register.domain.RegisterRepository
import cl.cencosud.blogapp.android.core.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class RegisterViewModel(private val repository: RegisterRepository) : ViewModel() {

    private val _registerStates = MutableLiveData<RegisterUiState>(RegisterUiState.DefaultState)
    val registerStates : LiveData<RegisterUiState> = _registerStates


    fun signUp(email: String, password: String, username: String) = viewModelScope.launch {
        _registerStates.value = RegisterUiState.Loading
        try {
            repository.signUp(email, password, username)
            _registerStates.value = RegisterUiState.Success
        } catch (error: Exception) {
            _registerStates.value =  RegisterUiState.Error(error)
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