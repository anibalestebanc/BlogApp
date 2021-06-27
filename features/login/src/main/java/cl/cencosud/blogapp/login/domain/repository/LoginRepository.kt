package cl.cencosud.blogapp.login.domain.repository

import cl.cencosud.blogapp.userinfo.domain.model.DomainUser

interface LoginRepository {
    suspend fun signIn(email: String, password: String): DomainUser
}