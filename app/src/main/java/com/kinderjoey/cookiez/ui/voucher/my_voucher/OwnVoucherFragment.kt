package com.kinderjoey.cookiez.ui.voucher.my_voucher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentOwnVoucherBinding
import com.kinderjoey.cookiez.ui.voucher.VoucherViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OwnVoucherFragment : Fragment() {

    private val binding by viewBinding<FragmentOwnVoucherBinding>()
    private val viewModel: VoucherViewModel by viewModels()
    private val uid = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_own_voucher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            if (uid != null) {
                viewModel.getUserVoucher(uid).let {

                    withContext(Dispatchers.Main) {
                        val ownAdapter = MyVoucherAdapter(it)

                        binding.rvOwnVoucher.apply {
                            adapter = ownAdapter
                            layoutManager =
                                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        }
                    }
                }
            }
        }
    }
}