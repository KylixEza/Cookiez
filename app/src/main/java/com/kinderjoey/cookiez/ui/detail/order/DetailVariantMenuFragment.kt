package com.kinderjoey.cookiez.ui.detail.order

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.VariantAdapter
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentDetailVariantMenuBinding
import org.koin.android.ext.android.inject

class DetailVariantMenuFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailVariantMenuBinding>()
    private val viewModel by viewModels<DetailVariantMenuViewModel>()
    private lateinit var variantAdapter: VariantAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_variant_menu, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        variantAdapter = VariantAdapter(viewModel, binding, this@DetailVariantMenuFragment)

        binding.includeAppBarMiddle.apply {
            ivFavorite.visibility = View.GONE
            tvTittle.text = "Detail Pemesanan"
        }
        binding.includeBottomBarDetail.apply {
            availability.text = "Harga Bahan"
            btnOrder.text = "Lanjutkan"
        }

        val menuName = ""
        observeVariant(menuName)
    }

    private fun observeVariant(menuName: String) {
        viewModel.getVariantMenu(menuName).observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Empty -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> it.data?.let { variant -> variantAdapter.setAllData(variant) }
            }
        })
    }
}