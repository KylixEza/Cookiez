package com.kinderjoey.cookiez.ui.voucher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.VoucherPagerAdapter
import com.kinderjoey.cookiez.databinding.FragmentVoucherBinding
import com.kinderjoey.cookiez.ui.voucher.voucher.AvailableVoucherFragment
import com.kinderjoey.cookiez.ui.voucher.my_voucher.OwnVoucherFragment
import kotlinx.android.synthetic.main.dialog_voucher.view.*

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

        binding.includeAppBarVoucher.ivInfo.setOnClickListener {
            val materialBuilder = MaterialAlertDialogBuilder(requireContext()).create()
            val inflater = layoutInflater.inflate(R.layout.dialog_voucher, null)
            inflater.btn_ok.setOnClickListener {
                materialBuilder.dismiss()
            }
            inflater.ib_close.setOnClickListener {
                materialBuilder.dismiss()
            }
            materialBuilder.setView(inflater)
            materialBuilder.show()
        }

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