package com.kinderjoey.cookiez.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.viewpager2.widget.ViewPager2
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.OnBoardingViewPagerAdapter
import com.kinderjoey.cookiez.databinding.FragmentOnBoardingBinding
import com.kinderjoey.cookiez.ui.onboard.screen.FirstScreenFragment
import com.kinderjoey.cookiez.ui.onboard.screen.SecondScreenFragment
import com.kinderjoey.cookiez.ui.onboard.screen.ThirdScreenFragment

class OnBoardingFragment : Fragment() {
    private val onBoardingBinding by viewBinding<FragmentOnBoardingBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.statusBarColor = View.GONE
        return inflater.inflate(R.layout.fragment_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfFragment = listOf(
            FirstScreenFragment(),
            SecondScreenFragment(),
            ThirdScreenFragment()
        )

        val adapter = OnBoardingViewPagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycle
        )

        adapter.apply {
            setAllFragments(listOfFragment)
            onBoardingBinding.viewPager.adapter = this
        }

        onBoardingBinding.apply {
            pageIndicatorView.count = listOfFragment.size
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    pageIndicatorView.setSelected(position)
                }
            })
        }
    }
}