package cl.cencosud.blogapp.login.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.cencosud.blogapp.android.di.ViewModelFactory
import cl.cencosud.blogapp.android.di.ViewModelKey
import cl.cencosud.blogapp.login.data.LoginRepositoryImpl
import cl.cencosud.blogapp.login.data.remote.LoginRemoteImpl
import cl.cencosud.blogapp.login.data.source.LoginRemote
import cl.cencosud.blogapp.login.domain.repository.LoginRepository
import cl.cencosud.blogapp.login.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@Module
@DisableInstallInCheck
abstract class LoginModule {
    @Binds
    abstract fun provideLoginRemote(loginRemoteImpl: LoginRemoteImpl): LoginRemote

    @Binds
    abstract fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel
}