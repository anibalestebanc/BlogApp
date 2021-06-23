package cl.cencosud.blogapp.home.data.home

import cl.cencosud.blogapp.android.core.Result
import cl.cencosud.blogapp.android.data.model.Post
import cl.cencosud.blogapp.home.domain.home.HomeScreenRepo

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource):
    HomeScreenRepo {

    override suspend fun getLatestPosts(): Result<List<Post>> = dataSource.getLatestPosts()
}