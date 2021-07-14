package cl.cencosud.blogapp.home.presentation.home

import androidx.lifecycle.*
import cl.cencosud.blogapp.home.domain.home.HomeRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repo: HomeRepository) : ViewModel() {

    private val _homeStates = MutableLiveData<HomeUiState>(HomeUiState.DefaultState)
    val homeStates: LiveData<HomeUiState> = _homeStates

    init {
        fetchLatestPosts()
    }

    private fun fetchLatestPosts() = viewModelScope.launch {
        _homeStates.value = HomeUiState.Loading
        try {
            val result = repo.getLatestPosts()
            if (result.isEmpty()) {
                _homeStates.value = HomeUiState.EmptyList
            } else {
                _homeStates.value = HomeUiState.Success(result)
            }
        } catch (error: Exception) {
            _homeStates.value = HomeUiState.Error(error)
        }
    }
}



