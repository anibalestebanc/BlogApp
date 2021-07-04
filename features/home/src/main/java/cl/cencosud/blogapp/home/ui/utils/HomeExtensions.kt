package cl.cencosud.blogapp.home.ui.utils

import cl.cencosud.blogapp.android.di.DaggerDependencies
import cl.cencosud.blogapp.home.ui.camera.CameraFragment
import cl.cencosud.blogapp.home.ui.di.DaggerHomeComponent
import cl.cencosud.blogapp.home.ui.home.HomeFragment
import dagger.hilt.android.EntryPointAccessors

internal fun HomeFragment.inject(){
    DaggerHomeComponent.builder()
        .context(requireContext())
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(), DaggerDependencies::class.java
            )
        )
        .build()
        .inject(this)
}

internal fun CameraFragment.inject(){
    DaggerHomeComponent.builder()
        .context(requireContext())
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(), DaggerDependencies::class.java
            )
        )
        .build()
        .inject(this)
}