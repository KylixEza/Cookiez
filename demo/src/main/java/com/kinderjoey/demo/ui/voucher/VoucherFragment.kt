package com.kinderjoey.demo.ui.voucher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentVoucherBinding
import com.kinderjoey.demo.ui.onboard.OnBoardingViewPagerAdapter
import com.kinderjoey.demo.ui.voucher.screen.AvailableVoucherFragment
import com.kinderjoey.demo.ui.voucher.screen.OwnVoucherFragment

class VoucherFragment : Fragment() {

    private val binding by viewBinding<FragmentVoucherBinding>()

    companion object {
        val tabTitles = listOf<String>(
            "Voucher Tersedia",
            "Voucher Saya"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voucher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VoucherPagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val listOfFragment = listOf<Fragment>(
            AvailableVoucherFragment(),
            OwnVoucherFragment()
        )

        binding.apply {
            adapter.apply {
                setFragments(listOfFragment)
                viewPager.adapter = this
            }
            TabLayoutMediator(tabVoucher, viewPager) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()
        }


    }
}