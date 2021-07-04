package cl.cencosud.blogapp.home.domain.camera

import android.graphics.Bitmap
import javax.inject.Inject

class UploadPhotoUseCase @Inject constructor(private val repository: CameraRepository) {
    suspend fun invoke(imageBitmap: Bitmap, description: String) =
        repository.uploadPhoto(imageBitmap, description)
}