package com.kinderjoey.cookiez.ui.voucher.voucher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentAvailableVoucherBinding
import com.kinderjoey.cookiez.model.Voucher
import com.kinderjoey.cookiez.ui.voucher.VoucherViewModel

class AvailableVoucherFragment : Fragment() {

    private val binding by viewBinding<FragmentAvailableVoucherBinding>()
    private val viewModel: VoucherViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_available_voucher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getListVoucher().observe(viewLifecycleOwner,::setViewAvailableVoucher)



    }

    private fun setViewAvailableVoucher(listItem: ArrayList<Voucher>) {
        val availableAdapter = VoucherAdapter(listItem)

        binding.rvAvailableVoucher.apply {
            adapter = availableAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }
}