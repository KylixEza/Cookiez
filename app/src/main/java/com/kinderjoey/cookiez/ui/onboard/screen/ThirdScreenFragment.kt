package com.kinderjoey.cookiez.ui.onboard.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.findNavController
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentThirdScreenBinding
import com.kinderjoey.cookiez.ui.onboard.OnBoardingFragmentDirections

class ThirdScreenFragment : Fragment() {

    private val thirdScreenBinding by viewBinding<FragmentThirdScreenBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.statusBarColor = View.GONE
        return inflater.inflate(R.layout.fragment_third_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thirdScreenBinding.apply {
            btnLogin.setOnClickListener {
                view.findNavController().navigate(
                    OnBoardingFragmentDirections.actionOnBoardingDestinationToLoginFragment()
                )
            }
            btnRegister.setOnClickListener {
                view.findNavController().navigate(
                    OnBoardingFragmentDirections.actionOnBoardingDestinationToRegisterFragment()
                )
            }
        }
    }
}