package cl.cencosud.blogapp.android.di

import cl.cencosud.blogapp.userinfo.data.source.UserCache
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DaggerDependencies {
    fun provideUserInfo(): UserCache
}