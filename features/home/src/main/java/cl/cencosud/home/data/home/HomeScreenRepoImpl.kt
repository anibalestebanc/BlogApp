package cl.cencosud.home.data.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import cl.cencosud.home.domain.home.HomeScreenRepo

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource):
    HomeScreenRepo {

    override suspend fun getLatestPosts(): Result<List<Post>> = dataSource.getLatestPosts()
}