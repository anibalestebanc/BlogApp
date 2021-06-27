package cl.cencosud.blogapp.userinfo.data.cache

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import cl.cencosud.blogapp.userinfo.data.cache.UserCacheConstants.KEY_EMAIL
import cl.cencosud.blogapp.userinfo.data.cache.UserCacheConstants.KEY_NAME
import cl.cencosud.blogapp.userinfo.data.source.UserCache
import cl.cencosud.blogapp.userinfo.domain.model.DomainUser
import cl.cencosud.blogapp.userinfo.domain.model.EmptyUserException

class UserCacheImpl(context: Application) : UserCache {

    companion object {
        const val PREFERENCES_NAME = "USER_SAVE_PREFERENCES"
    }
    private val preferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)


    override fun saveUser(user: DomainUser) {
        edit {
            putString(KEY_NAME, user.username)
            putString(KEY_EMAIL, user.email)
        }
    }

    override fun getUser(): DomainUser {
        val email = preferences.getString(KEY_EMAIL, null)
            ?: throw EmptyUserException("user not logged in")
        val username = preferences.getString(KEY_NAME, "")
        return DomainUser(email, username)
    }
    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }
}