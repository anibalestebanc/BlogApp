package cl.cencosud.blogapp.userinfo.domain

import cl.cencosud.blogapp.userinfo.domain.repository.UserRepository

class GetUserUseCase(private val repository: UserRepository) {
    suspend fun invoke() = repository.getLocalUser()
}