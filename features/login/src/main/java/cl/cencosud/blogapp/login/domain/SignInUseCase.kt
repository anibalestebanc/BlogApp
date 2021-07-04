package cl.cencosud.blogapp.login.domain

import cl.cencosud.blogapp.login.domain.repository.LoginRepository
import cl.cencosud.blogapp.userinfo.domain.model.DomainUser
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend fun invoke(email: String, password: String): DomainUser =
        repository.signIn(email, password)
}