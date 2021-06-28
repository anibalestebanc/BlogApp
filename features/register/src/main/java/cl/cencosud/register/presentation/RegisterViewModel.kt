package cl.cencosud.register.presentation

import androidx.lifecycle.*
import cl.cencosud.register.domain.SignUpUseCase
import cl.cencosud.register.domain.model.DomainNewUser
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class RegisterViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _registerStates = MutableLiveData<RegisterUiState>(RegisterUiState.DefaultState)
    val registerStates: LiveData<RegisterUiState> = _registerStates


    fun signUp(newUser: DomainNewUser) = viewModelScope.launch {
        _registerStates.value = RegisterUiState.Loading
        try {
            signUpUseCase.invoke(newUser)
            _registerStates.value = RegisterUiState.Success
        } catch (error: Exception) {
            _registerStates.value = RegisterUiState.Error(error)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class RegisterModelFactory(private val signUpUseCase: SignUpUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(signUpUseCase) as T
        }
        throw IllegalArgumentException("view model not found")
    }
}