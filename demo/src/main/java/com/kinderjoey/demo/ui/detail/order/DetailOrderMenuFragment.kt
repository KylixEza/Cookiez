package com.kinderjoey.demo.ui.detail.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentDetailOrderMenuBinding

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

        binding.includeItemListVariant.apply {
            rbVariant.visibility = View.GONE
            tvTittleVariant.text = "Bahan Komplit (3-5 Porsi)"
            tvSubtitleVariant.text = "Sayur-sayuran, telur, nasi, cabai, daging, jeruk"
            tvVariantPrice.text = "15.000"
            Glide.with(requireActivity())
                .load(resources.getDrawable(R.drawable.nasi_goreng_asia))
                .centerCrop()
                .into(ivVariantImg)
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