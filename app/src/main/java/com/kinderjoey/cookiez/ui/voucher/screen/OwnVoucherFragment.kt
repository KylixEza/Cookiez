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
import com.kinderjoey.cookiez.databinding.FragmentOwnVoucherBinding
import com.kinderjoey.cookiez.ui.voucher.screen.VoucherListAdapter

class OwnVoucherFragment : Fragment() {

    private val binding by viewBinding<FragmentOwnVoucherBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_own_voucher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ownAdapter = VoucherListAdapter()

        binding.rvOwnVoucher.apply {
            adapter = ownAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        ownAdapter.setAllData(DataDummy.setOwnVoucher())
    }

}