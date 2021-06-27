package cl.cencosud.blogapp.android.presentation

import androidx.lifecycle.*
import cl.cencosud.blogapp.android.core.Event
import cl.cencosud.blogapp.android.presentation.model.MainEffect
import cl.cencosud.blogapp.android.presentation.model.MainUiState
import cl.cencosud.blogapp.userinfo.domain.GetUserUseCase
import cl.cencosud.blogapp.userinfo.domain.model.EmptyUserException
import kotlinx.coroutines.launch

class MainViewModel(private val getUserUseCase: GetUserUseCase) : ViewModel() {

    private val _mainStates = MutableLiveData<MainUiState>(MainUiState.DefaultState)
    val mainStates: LiveData<MainUiState> = _mainStates

    private val _mainEffect = MutableLiveData<Event<MainEffect>>()
    val mainEffect: LiveData<Event<MainEffect>> = _mainEffect

    init {
        initApp()
    }

    private fun initApp() = viewModelScope.launch {
        _mainStates.value = MainUiState.Loading
        try {
            getUserUseCase.invoke()
            _mainEffect.value = Event(MainEffect.GotoHome)
        } catch (error: Exception) {
            checkException(error)
        }
    }

    private fun checkException(error: Exception) = when (error) {
        is EmptyUserException -> _mainEffect.value = Event(MainEffect.GoToLogin)
        else -> _mainStates.value = MainUiState.Error(error)
    }
}


@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val getUserUseCase: GetUserUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getUserUseCase) as T
        }
        throw IllegalArgumentException("view model not found")
    }
}