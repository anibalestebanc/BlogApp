package cl.cencosud.register.ui.di

import android.content.Context
import cl.cencosud.blogapp.android.di.DaggerDependencies
import cl.cencosud.register.ui.RegisterFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DaggerDependencies::class],
    modules = [
        RegisterModule::class
    ]
)
interface RegisterComponent {
    fun inject(fragment: RegisterFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(loginModuleDependencies: DaggerDependencies): Builder
        fun build(): RegisterComponent
    }
}