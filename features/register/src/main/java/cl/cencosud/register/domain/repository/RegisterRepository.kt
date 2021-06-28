package cl.cencosud.register.domain.repository

import cl.cencosud.register.domain.model.DomainNewUser

interface RegisterRepository {
    suspend fun signUp(newUser: DomainNewUser)
}