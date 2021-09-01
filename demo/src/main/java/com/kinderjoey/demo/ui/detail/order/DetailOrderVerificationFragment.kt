package com.kinderjoey.demo.ui.detail.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentDetailOrderVerificationBinding

class DetailOrderVerificationFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailOrderVerificationBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_order_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeAppBarMiddle.apply {
            ivFavorite.visibility = View.GONE
            tvTittle.text = "Pesanan Diterima"
        }
    }
}