package cl.cencosud.blogapp.userinfo.data.cache

import android.content.Context
import android.content.SharedPreferences
import cl.cencosud.blogapp.userinfo.data.cache.UserCacheConstants.KEY_EMAIL
import cl.cencosud.blogapp.userinfo.data.cache.UserCacheConstants.KEY_ID
import cl.cencosud.blogapp.userinfo.data.cache.UserCacheConstants.KEY_PHOTO_URL
import cl.cencosud.blogapp.userinfo.data.cache.UserCacheConstants.KEY_USERNAME
import cl.cencosud.blogapp.userinfo.data.source.UserCache
import cl.cencosud.blogapp.userinfo.domain.model.DomainUser
import cl.cencosud.blogapp.userinfo.domain.model.EmptyUserException

class UserCacheImpl(context: Context) : UserCache {

    companion object {
        const val PREFERENCES_NAME = "USER_SAVE_PREFERENCES"
    }

    private val preferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)


    override fun saveUser(user: DomainUser) {
        edit {
            putString(KEY_ID, user.id)
            putString(KEY_USERNAME, user.username)
            putString(KEY_EMAIL, user.email)
            putString(KEY_PHOTO_URL, user.photo_url)
        }
    }

    override fun getUser(): DomainUser {
        val email = preferences.getString(KEY_EMAIL, null)
            ?: throw EmptyUserException("user not logged in")
        val username = preferences.getString(KEY_USERNAME, "").orEmpty()
        val photo = preferences.getString(KEY_PHOTO_URL, "").orEmpty()
        val userId = preferences.getString(KEY_ID, "").orEmpty()
        return DomainUser(email = email, username = username, photo_url = photo, id = userId)
    }

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }
}