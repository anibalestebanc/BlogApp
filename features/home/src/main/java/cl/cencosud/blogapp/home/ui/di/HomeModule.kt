package cl.cencosud.blogapp.home.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.cencosud.blogapp.android.di.ViewModelFactory
import cl.cencosud.blogapp.android.di.ViewModelKey
import cl.cencosud.blogapp.home.data.camera.CameraRepositoryImpl
import cl.cencosud.blogapp.home.data.home.HomeRepositoryImpl
import cl.cencosud.blogapp.home.data.home.remote.HomeRemoteImpl
import cl.cencosud.blogapp.home.data.home.source.HomeRemote
import cl.cencosud.blogapp.home.domain.camera.CameraRepository
import cl.cencosud.blogapp.home.domain.home.HomeRepository
import cl.cencosud.blogapp.home.presentation.camera.CameraViewModel
import cl.cencosud.blogapp.home.presentation.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@Module
@DisableInstallInCheck
abstract class HomeModule {


    @Binds
    abstract fun provideCameraRepository(repositoryImpl: CameraRepositoryImpl): CameraRepository


    @Binds
    abstract fun provideRemote(remoteImpl: HomeRemoteImpl): HomeRemote

    @Binds
    abstract fun provideRepository(repositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CameraViewModel::class)
    internal abstract fun provideCameraViewModel(viewModel: CameraViewModel): ViewModel
}