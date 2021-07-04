package cl.cencosud.profile.ui.di

import android.content.Context
import cl.cencosud.blogapp.android.di.DaggerDependencies
import cl.cencosud.profile.ui.SetupProfileFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DaggerDependencies::class],
    modules = [
        ProfileModule::class
    ]
)
interface ProfileComponent {

    fun inject(fragment: SetupProfileFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(loginModuleDependencies: DaggerDependencies): Builder
        fun build(): ProfileComponent
    }
}