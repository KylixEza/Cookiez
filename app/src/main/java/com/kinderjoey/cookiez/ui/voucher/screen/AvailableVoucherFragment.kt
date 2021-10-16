package com.kinderjoey.cookiez.ui.voucher.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.data.sources.dummy.DataDummy
import com.kinderjoey.cookiez.databinding.FragmentAvailableVoucherBinding
import com.kinderjoey.cookiez.ui.voucher.screen.VoucherListAdapter

class AvailableVoucherFragment : Fragment() {

    private val binding by viewBinding<FragmentAvailableVoucherBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_available_voucher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val availableAdapter = VoucherListAdapter()

        binding.rvAvailableVoucher.apply {
            adapter = availableAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        availableAdapter.setAllData(DataDummy.setAvailableVoucher())
    }
}