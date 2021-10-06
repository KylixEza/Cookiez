package com.kinderjoey.cookiez.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentLoginBinding
import com.kinderjoey.cookiez.ui.splash.SplashFragmentDirections

class LoginFragment : Fragment() {

    private val loginBinding by viewBinding<FragmentLoginBinding>()
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.statusBarColor = View.GONE
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBinding.btnLogin.setOnClickListener {
            view.findNavController().navigate(LoginFragmentDirections.actionLoginDestinationToBaseDestination())
            loginViewModel.saveToDataStore("Kylix")
            activity?.finish()
        }
    }
}