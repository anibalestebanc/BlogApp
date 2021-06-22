package cl.cencosud.home.data

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import cl.cencosud.home.data.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: cl.cencosud.home.data.HomeScreenDataSource):
    cl.cencosud.home.domain.HomeScreenRepo {

    override suspend fun getLatestPosts(): Result<List<Post>> = dataSource.getLatestPosts()
}