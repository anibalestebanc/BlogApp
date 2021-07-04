package cl.cencosud.profile.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.cencosud.blogapp.android.di.ViewModelFactory
import cl.cencosud.blogapp.android.di.ViewModelKey
import cl.cencosud.profile.data.ProfileRepositoryImpl
import cl.cencosud.profile.domain.ProfileRepository
import cl.cencosud.profile.presentation.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@Module
@DisableInstallInCheck
abstract class ProfileModule {



    @Binds
    abstract fun provideRepository(repositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    internal abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun provideViewModel(viewModel: ProfileViewModel): ViewModel
}