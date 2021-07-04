package cl.cencosud.register.domain

import cl.cencosud.register.domain.model.DomainNewUser
import cl.cencosud.register.domain.repository.RegisterRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: RegisterRepository) {
    suspend fun invoke(newUser: DomainNewUser) = repository.signUp(newUser)
}