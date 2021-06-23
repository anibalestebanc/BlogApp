package cl.cencosud.blogapp.home.presentation

import androidx.lifecycle.*
import cl.cencosud.blogapp.android.core.Result
import cl.cencosud.blogapp.home.domain.home.HomeScreenRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class HomeScreenViewModel(private val repo: HomeScreenRepo): ViewModel() {

    fun fetchLatestPosts() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Result.Loading())
        try {
            emit(repo.getLatestPosts())
        }catch (e: Exception){
            emit(Result.Failure(e))
        }
    }
}


class HomeScreenViewModelFactory(private val repo: HomeScreenRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)
    }

}


