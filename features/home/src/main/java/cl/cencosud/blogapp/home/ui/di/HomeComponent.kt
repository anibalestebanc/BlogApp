package cl.cencosud.blogapp.home.ui.di

import android.content.Context
import cl.cencosud.blogapp.android.di.DaggerDependencies
import cl.cencosud.blogapp.home.ui.camera.CameraFragment
import cl.cencosud.blogapp.home.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DaggerDependencies::class],
    modules = [
        HomeModule::class
    ]
)
interface HomeComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: CameraFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(loginModuleDependencies: DaggerDependencies): Builder
        fun build(): HomeComponent
    }
}