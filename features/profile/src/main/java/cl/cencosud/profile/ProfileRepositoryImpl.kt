package cl.cencosud.profile

import android.graphics.Bitmap

class ProfileRepositoryImpl(private val dataSource: ProfileDataSource) : ProfileRepository {
  override suspend fun updateProfile(imageBitmap: Bitmap, username: String) = dataSource.updateUserProfile(imageBitmap,username)
}