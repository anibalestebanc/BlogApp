package cl.cencosud.blogapp.login.domain

import cl.cencosud.blogapp.login.domain.repository.LoginRepository
import cl.cencosud.blogapp.userinfo.domain.model.DomainUser

class SignInUseCase(private val repository: LoginRepository) {
    suspend fun invoke(email: String, password: String) : DomainUser =
        repository.signIn(email, password)
}