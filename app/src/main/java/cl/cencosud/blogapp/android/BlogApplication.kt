package cl.cencosud.blogapp.android

import android.app.Application
import cl.cencosud.blogapp.userinfo.data.cache.UserCacheImpl
import cl.cencosud.blogapp.userinfo.data.source.UserCache

class BlogApplication : Application() {
    val userInfo: UserCache by lazy { UserCacheImpl(this) }

    override fun onCreate() {
        super.onCreate()
    }
}