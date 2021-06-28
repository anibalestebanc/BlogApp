package cl.cencosud.register.domain

import cl.cencosud.register.domain.model.DomainNewUser
import cl.cencosud.register.domain.repository.RegisterRepository

class SignUpUseCase(private val repository: RegisterRepository) {
    suspend fun invoke(newUser: DomainNewUser) = repository.signUp(newUser)
}