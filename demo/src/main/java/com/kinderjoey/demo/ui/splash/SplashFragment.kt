package com.kinderjoey.demo.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.kinderjoey.demo.MainActivity
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentSplashBinding
import com.kinderjoey.demo.ui.onboard.OnBoardingFragment

class SplashFragment : Fragment() {

    private val splashBinding by viewBinding<FragmentSplashBinding>()
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.statusBarColor = View.GONE
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            splashViewModel.readFromDataStore().observe(viewLifecycleOwner, { isLogin ->
                if (isLogin) {
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                } else {
                    val mFragment = OnBoardingFragment()
                    val mFragmentManager = parentFragmentManager
                    mFragmentManager.commit {
                        replace(R.id.splash_fragment_container, mFragment, OnBoardingFragment::class.java.simpleName)
                    }
                }
            })
        }, 1500)
    }
}