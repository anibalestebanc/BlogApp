package cl.cencosud.blogapp.home.data.home

import cl.cencosud.blogapp.android.data.model.Post
import cl.cencosud.blogapp.home.data.home.remote.HomeRemoteImpl
import cl.cencosud.blogapp.home.domain.home.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val dataSource: HomeRemoteImpl) :
    HomeRepository {
    override suspend fun getLatestPosts(): List<Post> = dataSource.getLatestPosts()
}