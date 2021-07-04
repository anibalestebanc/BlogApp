package cl.cencosud.blogapp.android.di

import android.content.Context
import cl.cencosud.blogapp.userinfo.data.cache.UserCacheImpl
import cl.cencosud.blogapp.userinfo.data.source.UserCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class BlogModule {

    @Provides
    fun provideUserInfo(
        @ApplicationContext context: Context
    ): UserCache = UserCacheImpl(context)

}