package cl.cencosud.blogapp.userinfo.domain.repository

import cl.cencosud.blogapp.userinfo.domain.model.DomainUser

interface UserRepository {
    suspend fun getLocalUser(): DomainUser
}