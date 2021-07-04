package cl.cencosud.blogapp.login.ui.di

import android.content.Context
import cl.cencosud.blogapp.android.di.DaggerDependencies
import cl.cencosud.blogapp.login.ui.LoginFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DaggerDependencies::class],
    modules = [
        LoginModule::class
    ]
)
interface LoginComponent {

    fun inject(fragment: LoginFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(loginModuleDependencies: DaggerDependencies): Builder
        fun build(): LoginComponent
    }
}