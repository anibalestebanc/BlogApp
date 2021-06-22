package cl.cencosud.home.domain

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post

interface HomeScreenRepo {
    suspend fun getLatestPosts(): Result<List<Post>>
}