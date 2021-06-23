package cl.cencosud.blogapp.android

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cl.cencosud.blogapp.android.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding get() = _binding!!
    private var _binding : FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

   /*     val installMonitor = DynamicInstallMonitor()*/

        binding.loginButton.setOnClickListener {

            findNavController().navigate(R.id.go_to_login)

    /*        findNavController().navigate(R.id.go_to_login, null, null, DynamicExtras(installMonitor) )
            if (installMonitor.isInstallRequired){
                installMonitor.status.observe(viewLifecycleOwner, object : Observer<SplitInstallSessionState> {
                    override fun onChanged(sessionState: SplitInstallSessionState) {
                        when (sessionState.status()) {
                            SplitInstallSessionStatus.INSTALLED -> {
                                // Call navigate again here or after user taps again in the UI:
                                 findNavController().navigate(R.id.go_to_login)
                            }
                            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                               // SplitInstallManager.startConfirmationDialogForResult()
                            }

                            // Handle all remaining states:
                            SplitInstallSessionStatus.FAILED -> {

                            }
                            SplitInstallSessionStatus.CANCELED -> {

                            }
                        }

                        if (sessionState.hasTerminalStatus()) {
                            installMonitor.status.removeObserver(this);
                        }
                    }
                });
            }*/
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}