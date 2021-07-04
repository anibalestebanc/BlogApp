package cl.cencosud.blogapp.home.data.camera

import android.graphics.Bitmap
import cl.cencosud.blogapp.home.data.camera.remote.CameraRemoteImpl
import cl.cencosud.blogapp.home.domain.camera.CameraRepository
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor(private val dataSource: CameraRemoteImpl) :
    CameraRepository {
    override suspend fun uploadPhoto(imageBitmap: Bitmap, description: String) {
        dataSource.uploadPhoto(imageBitmap, description)
    }
}