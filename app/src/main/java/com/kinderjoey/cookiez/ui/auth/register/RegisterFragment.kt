package com.kinderjoey.cookiez.ui.auth.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentRegisterBinding
import com.kinderjoey.cookiez.model.User
import com.kinderjoey.cookiez.ui.BaseActivity
import com.kinderjoey.cookiez.ui.auth.login.LoginFragment
import com.kinderjoey.cookiez.util.Constanta
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var email = ""
    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = View.GONE
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            activity?.apply {

                btnRegister.setOnClickListener {
                    val email = edtEmail.editText?.text.toString()
                    this@RegisterFragment.email = email
                    val password = edtPassword.editText?.text.toString()
                    val username = edtName.editText?.text.toString()
                    val address = edtAddress.editText?.text.toString()
                    val phoneNumber = edtPhoneNumber.editText?.text.toString()

                    viewModel.signUp(
                        email,
                        password,
                        createEntityUser(email,username,address,phoneNumber)
                    ).observe(viewLifecycleOwner,::signUpResponse)
                }

                val callback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        parentFragmentManager.commit {
                            replace(R.id.auth_container, LoginFragment())
                        }
                    }
                }
                if (Constanta.SOURCE == Constanta.SOURCE_LOGOUT) {
                    onBackPressedDispatcher.addCallback(requireActivity(), callback)
                }
            }

            tvLogin.setOnClickListener {
                if (Constanta.SOURCE == Constanta.SOURCE_LOGOUT) {
                    parentFragmentManager.commit {
                        replace(R.id.auth_container, LoginFragment())
                    }
                } else {
                    view.findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                }
            }
        }
    }

    private fun createEntityUser(
        email: String,
        username: String,
        address: String,
        phoneNumber: String
    ):User{
        return User(
            id = "",
            email = email,
            name = username,
            address = address,
            phoneNumber = phoneNumber
        )
    }
    private fun signUpResponse(resource: Resource<Unit>) {
        when(resource){
            is Resource.Success -> {
                loadingState(false)
                if (Constanta.SOURCE == Constanta.SOURCE_LOGOUT)
                    startActivity(Intent(requireContext(), BaseActivity::class.java))
                else
                    view?.findNavController()?.navigate(RegisterFragmentDirections.actionRegisterDestinationToBaseDestination())
                viewModel.savePrefEmail(email)
                viewModel.savePrefHaveRunAppBefore(true)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loadingState(state: Boolean) {
        with(binding){
            progressBar.isVisible = state
            btnRegister.isEnabled = !state
        }
    }


}