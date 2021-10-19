package com.kinderjoey.cookiez.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentLoginBinding
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val binding by viewBinding<FragmentLoginBinding>()
    private val loginViewModel: LoginViewModel by viewModel()

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

        binding.apply {
            btnLogin.setOnClickListener {

                val email = edtEmail.editText?.text.toString()
                val password = edtPassword.editText?.text.toString()
                loginViewModel.signIn(email,password).observe(viewLifecycleOwner,::signInResponse)
                loginViewModel.savePrefEmail(email)
                loginViewModel.savePrefHaveRunAppBefore(true)
            }
            tvRegister.setOnClickListener {
                view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }

    }

    private fun signInResponse(resource: Resource<Unit>) {
        when(resource){
            is Resource.Success -> {
                loadingState(false)
                view?.findNavController()?.navigate(LoginFragmentDirections.actionLoginDestinationToBaseDestination())
//                loginViewModel.saveToDataStore("Kylix")
                activity?.finish()
            }
            is Resource.Loading -> {
                loadingState(true)
            }

            is Resource.Error -> {
                loadingState(false)
                Snackbar.make(binding.root,resource.message.toString(), Snackbar.LENGTH_LONG).show()
            }

        }
    }
    private fun loadingState(state: Boolean) {
        with(binding){
            progressBar.isVisible = state
            btnLogin.isEnabled = !state
        }
    }
}