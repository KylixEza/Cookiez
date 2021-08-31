package com.kinderjoey.demo.ui.detail.order

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentDetailVariantMenuBinding

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
        binding.includeItemListVariant1.rbVariant.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.apply {
                includeBottomBarDetail.availabilityStatus.text = "15.000"
            }
        }
        binding.includeBottomBarDetail.btnOrder.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.detail_container, DetailOrderMenuFragment(), DetailOrderMenuFragment::class.java.simpleName)
            }
        }
        binding.apply {
            includeItemListVariant1.apply {
                tvTittleVariant.text = "Bahan Komplit (3-5 Porsi)"
                tvSubtitleVariant.text = "Sayur-sayuran, telur, nasi, cabai, daging, jeruk"
                tvVariantPrice.text = "15.000"
                Glide.with(requireActivity())
                    .load(resources.getDrawable(R.drawable.nasi_goreng_asia))
                    .centerCrop()
                    .into(ivVariantImg)
            }
            includeItemListVariant2.apply {
                tvTittleVariant.text = "Bahan Komplit (5-7 Porsi)"
                tvSubtitleVariant.text = "Sayur-sayuran, telur, nasi, cabai, daging, jeruk"
                tvVariantPrice.text = "20.000"
                Glide.with(requireActivity())
                    .load(resources.getDrawable(R.drawable.nasi_goreng_asia))
                    .centerCrop()
                    .into(ivVariantImg)
            }
            includeItemListVariant3.apply {
                tvTittleVariant.text = "Tanpa Nasi (3-5 Porsi)"
                tvSubtitleVariant.text = "Sayur-sayuran, telur, cabai, daging, jeruk"
                tvVariantPrice.text = "12.000"
                Glide.with(requireActivity())
                    .load(resources.getDrawable(R.drawable.nasi_goreng_asia))
                    .centerCrop()
                    .into(ivVariantImg)
            }

        }
    }
}