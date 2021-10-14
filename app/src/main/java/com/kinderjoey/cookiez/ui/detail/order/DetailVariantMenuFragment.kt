package com.kinderjoey.cookiez.ui.detail.order

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.VariantAdapter
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentDetailVariantMenuBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailVariantMenuFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailVariantMenuBinding>()
    private val viewModel by viewModel<DetailVariantMenuViewModel>()
    private lateinit var variantAdapter: VariantAdapter
    private val args by navArgs<DetailVariantMenuFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_variant_menu, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        variantAdapter = VariantAdapter(viewModel, binding, this@DetailVariantMenuFragment)

        binding.apply {
            includeAppBarMiddle.apply {
                ivFavorite.visibility = View.GONE
                tvTittle.text = "Detail Pemesanan"
            }
            includeBottomBarDetail.apply {
                availability.text = "Harga Bahan"
                btnOrder.text = "Lanjutkan"
            }
            rvVariant.apply {
                adapter = variantAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }

        val menuName = args.menuName
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