package cl.cencosud.blogapp.userinfo.data.source

import cl.cencosud.blogapp.userinfo.domain.model.DomainUser

interface UserCache {
    fun saveUser(user: DomainUser)
    fun getUser(): DomainUser
}