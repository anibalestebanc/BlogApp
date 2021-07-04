package cl.cencosud.blogapp.android.ui.di

import cl.cencosud.blogapp.userinfo.data.UserRepositoryImpl
import cl.cencosud.blogapp.userinfo.data.cache.UserCacheImpl
import cl.cencosud.blogapp.userinfo.data.source.UserCache
import cl.cencosud.blogapp.userinfo.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class MainModule {
    @Binds
    abstract fun provideUserRepositoryImpl(userRepositoryImpl: UserRepositoryImpl): UserRepository
}