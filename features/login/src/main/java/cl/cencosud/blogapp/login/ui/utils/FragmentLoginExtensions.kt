package cl.cencosud.blogapp.login.ui.utils

import cl.cencosud.blogapp.android.di.DaggerDependencies
import cl.cencosud.blogapp.login.ui.LoginFragment
import cl.cencosud.blogapp.login.ui.di.DaggerLoginComponent
import dagger.hilt.android.EntryPointAccessors

internal fun LoginFragment.inject() {
    DaggerLoginComponent.builder()
        .context(requireContext())
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(), DaggerDependencies::class.java
            )
        )
        .build()
        .inject(this)
}