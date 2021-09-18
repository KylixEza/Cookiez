package com.kinderjoey.cookiez.ui.detail.order

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentDetailVariantMenuBinding

class DetailVariantMenuFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailVariantMenuBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_variant_menu, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeAppBarMiddle.apply {
            ivFavorite.visibility = View.GONE
            tvTittle.text = "Detail Pemesanan"
        }
        binding.includeBottomBarDetail.apply {
            availability.text = "Harga Bahan"
            btnOrder.text = "Lanjutkan"
        }

        binding.includeBottomBarDetail.btnOrder.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.detail_container, DetailOrderMenuFragment(), DetailOrderMenuFragment::class.java.simpleName)
            }
        }
    }
}