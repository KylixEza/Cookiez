package com.kinderjoey.cookiez.ui.detail.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.commit
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentDetailOrderMenuBinding

class DetailOrderMenuFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailOrderMenuBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_order_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeAppBarMiddle.apply {
            tvTittle.text = "Detail Pemesanan"
            ivFavorite.visibility = View.INVISIBLE
        }

        binding.includeBottomBarDetailOrder.apply {
            btnOrderNow.setOnClickListener {
                parentFragmentManager.commit {
                    replace(R.id.detail_container, DetailOrderVerificationFragment(), DetailOrderMenuFragment::class.java.simpleName)
                }
            }
        }
    }
}