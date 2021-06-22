package cl.cencosud.profile

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.blogapp.core.Result
import kotlinx.coroutines.Dispatchers

class AuthViewModel(private val repo: ProfileRepository) : ViewModel() {


    fun updateUserProfile(imageBitmap: Bitmap, username: String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.updateProfile(imageBitmap,username)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

}


class AuthViewModelFactory(private val repo: ProfileRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ProfileRepository::class.java).newInstance(repo)
    }
}