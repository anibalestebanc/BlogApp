package cl.cencosud.blogapp.home.data.camera

import android.graphics.Bitmap
import cl.cencosud.blogapp.home.domain.camera.CameraRepo

class CameraRepoImpl(private val dataSource: CameraDataSource):
    CameraRepo {
    override suspend fun uploadPhoto(imageBitmap: Bitmap, description: String) {
        dataSource.uploadPhoto(imageBitmap, description)
    }
}