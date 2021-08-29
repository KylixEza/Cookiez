package com.kinderjoey.demo.ui.detail.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentDetailVariantMenuBinding

class DetailVariantMenuFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailVariantMenuBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_variant_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeAppBarMiddle.ivFavorite.visibility = View.GONE
        binding.includeBottomBarDetail.apply {
            availability.text = "Harga Bahan"
            btnOrder.text = "Lanjutkan"
        }
        binding.includeItemListVariant1.rbVariant.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.apply {
                includeBottomBarDetail.availabilityStatus.text = "15.000"
            }
        }
    }
}