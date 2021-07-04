package cl.cencosud.register.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.cencosud.blogapp.android.di.ViewModelFactory
import cl.cencosud.blogapp.android.di.ViewModelKey
import cl.cencosud.register.data.RegisterRepositoryImpl
import cl.cencosud.register.data.remote.RegisterRemoteImpl
import cl.cencosud.register.data.source.RegisterRemote
import cl.cencosud.register.domain.repository.RegisterRepository
import cl.cencosud.register.presentation.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@Module
@DisableInstallInCheck
abstract class RegisterModule {

    @Binds
    abstract fun provideRegisterRemote(remoteImpl: RegisterRemoteImpl): RegisterRemote

    @Binds
    abstract fun provideRegisterRepository(repositoryImpl: RegisterRepositoryImpl): RegisterRepository

    @Binds
    internal abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun provideViewModel(viewModel: RegisterViewModel): ViewModel
}