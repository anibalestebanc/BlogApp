package cl.cencosud.profile.presentation

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.cencosud.profile.domain.ProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val repo: ProfileRepository) : ViewModel() {
    private val _profileStates = MutableLiveData<ProfileUiState>(ProfileUiState.DefaultState)
    val profileStates: LiveData<ProfileUiState> = _profileStates

    fun updateUserProfile(imageBitmap: Bitmap, username: String) = viewModelScope.launch {
        _profileStates.value = ProfileUiState.Loading
        try {
            repo.updateProfile(imageBitmap, username)
            _profileStates.value = ProfileUiState.Success
        } catch (error: Exception) {
            _profileStates.value = ProfileUiState.Error(error)
        }
    }
}