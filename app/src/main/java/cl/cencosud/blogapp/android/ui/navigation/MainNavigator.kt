package cl.cencosud.blogapp.android.ui.navigation

import android.view.View
import androidx.navigation.findNavController
import cl.cencosud.blogapp.android.R
import cl.cencosud.blogapp.android.ui.MainActivity

object MainNavigator {

    fun navigateToHome(view: View, activity: MainActivity) {
        view.findNavController().navigate(R.id.go_to_home)
        activity.finish()
    }

    fun navigateToLogin(view: View) {
        view.findNavController().navigate(R.id.go_to_login)
    }
}