package cl.cencosud.blogapp.home.presentation.camera

import android.graphics.Bitmap
import androidx.lifecycle.*
import cl.cencosud.blogapp.home.domain.camera.CameraRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CameraViewModel @Inject constructor(private val repo: CameraRepository) : ViewModel() {
    private val _cameraStates = MutableLiveData<CameraUiState>(CameraUiState.DefaultState)
    val cameraStates: LiveData<CameraUiState> = _cameraStates

    fun uploadPhoto(imageBitmap: Bitmap, description: String) = viewModelScope.launch {
        _cameraStates.value = CameraUiState.Loading
        try {
            repo.uploadPhoto(imageBitmap, description)
            _cameraStates.value = CameraUiState.Success
        } catch (error: Exception) {
            _cameraStates.value = CameraUiState.Error(error)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class CameraViewModelFactory(private val repository: CameraRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CameraViewModel::class.java)) {
            return CameraViewModel(repository) as T
        }
        throw IllegalArgumentException("view model not found")
    }
}