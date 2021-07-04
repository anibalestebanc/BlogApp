package cl.cencosud.register.ui.utils

import cl.cencosud.blogapp.android.di.DaggerDependencies
import cl.cencosud.register.ui.RegisterFragment
import cl.cencosud.register.ui.di.DaggerRegisterComponent
import dagger.hilt.android.EntryPointAccessors

internal fun RegisterFragment.inject() {
    DaggerRegisterComponent.builder()
        .context(requireContext())
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(), DaggerDependencies::class.java
            )
        ).build()
        .inject(this)
}