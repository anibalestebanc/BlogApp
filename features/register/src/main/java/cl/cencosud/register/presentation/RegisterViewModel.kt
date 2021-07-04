package cl.cencosud.register.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.cencosud.register.domain.SignUpUseCase
import cl.cencosud.register.domain.model.DomainNewUser
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
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