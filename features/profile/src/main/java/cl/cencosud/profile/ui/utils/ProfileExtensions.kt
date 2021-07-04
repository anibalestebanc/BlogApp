package cl.cencosud.profile.ui.utils

import cl.cencosud.blogapp.android.di.DaggerDependencies
import cl.cencosud.profile.ui.SetupProfileFragment
import cl.cencosud.profile.ui.di.DaggerProfileComponent
import dagger.hilt.android.EntryPointAccessors

internal fun SetupProfileFragment.inject() {
    DaggerProfileComponent.builder()
        .context(requireContext())
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(), DaggerDependencies::class.java
            )
        ).build()
        .inject(this)
}