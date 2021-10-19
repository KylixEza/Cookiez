package com.kinderjoey.cookiez.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentRegisterBinding
import com.kinderjoey.cookiez.model.User
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
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

            activity.apply {

                btnRegister.setOnClickListener {
                    val email = edtEmail.editText?.text.toString()
                    val password = edtPassword.editText?.text.toString()
                    val username = edtName.editText?.text.toString()
                    val address = edtAddress.editText?.text.toString()
                    val phoneNumber = edtPhoneNumber.editText?.text.toString()

                    viewModel.signUp(
                        email,
                        password,
                        createEntityUser(email,username,address,phoneNumber)
                    ).observe(viewLifecycleOwner,::signUpResponse)

                    viewModel.savePrefEmail(email)
                    viewModel.savePrefHaveRunAppBefore(true)
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
                view?.findNavController()?.navigate(RegisterFragmentDirections.actionRegisterDestinationToBaseDestination())
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