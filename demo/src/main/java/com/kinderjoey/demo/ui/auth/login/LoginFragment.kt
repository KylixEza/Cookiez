package com.kinderjoey.demo.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.commit
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentLoginBinding
import com.kinderjoey.demo.ui.auth.register.RegisterFragment

class LoginFragment : Fragment() {

    private val loginBinding by viewBinding<FragmentLoginBinding>()

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

        loginBinding.tvRegister.setOnClickListener {
            val mFragmentManager = parentFragmentManager
            val mRegisterFragment = RegisterFragment()

            mFragmentManager.commit {
                replace(R.id.auth_container, mRegisterFragment, RegisterFragment::class.java.simpleName)
            }
        }
    }
}