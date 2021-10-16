package com.kinderjoey.cookiez.ui.detail.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentDetailOrderMenuBinding
import com.kinderjoey.cookiez.model.User
import com.kinderjoey.cookiez.model.Variant
import com.kinderjoey.cookiez.ui.profile.ProfileViewModel
import com.kinderjoey.cookiez.util.Formatting

class DetailOrderMenuFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailOrderMenuBinding>()
    private val args by navArgs<DetailOrderMenuFragmentArgs>()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val viewModel: ProfileViewModel by activityViewModels()
    private val uid = firebaseAuth.currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_order_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val variant = args.selectedVariant

        binding.apply {
            includeAppBarMiddle.apply {
                tvTittle.text = "Detail Pemesanan"
                ivFavorite.visibility = View.INVISIBLE
            }
            includeSelectedItemVariant.apply {
                tvTittleVariant.text = variant.variant
                tvSubtitleVariant.text = variant.composition
                tvVariantPrice.text = Formatting.rupiahCurrencyFormatting(variant.price)
                Glide.with(requireContext())
                    .load(variant.image)
                    .centerCrop()
                    .into(ivVariantImg)
            }
            tvIngredientsPrice.text = Formatting.rupiahCurrencyFormatting(variant.price)
        }
        viewModel.getUser(uid).observe(viewLifecycleOwner, observe(variant))

        binding.includeBottomBarDetailOrder.apply {
        }
    }

    private fun observe(variant: Variant): Observer<in User> = Observer {
        binding.tvDetailLocation.text = it.address
        binding.includeBottomBarDetailOrder.includePaymentCalculation.apply {
            tvCWalletValue.text = it.cookiezWallet.toString()
            tvCashValue.text = String.format("${variant.price - it.cookiezWallet}")
        }
    }
}