package cl.cencosud.blogapp.home.domain.home

import javax.inject.Inject

class GetLastPostsUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend fun invoke() = repository.getLatestPosts()
}