package cl.cencosud.blogapp.home.ui.myaccount

import android.view.View
import androidx.navigation.findNavController
import cl.cencosud.blogapp.home.R

object MyAccountNavigator {

    fun navigateToProfile(view: View) {
        view.findNavController().navigate(R.id.got_to_profile)
    }
}