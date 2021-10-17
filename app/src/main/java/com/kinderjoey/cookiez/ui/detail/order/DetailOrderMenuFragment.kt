package com.kinderjoey.cookiez.ui.detail.order

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentDetailOrderMenuBinding
import com.kinderjoey.cookiez.model.User
import com.kinderjoey.cookiez.ui.profile.ProfileViewModel
import com.kinderjoey.cookiez.util.Formatting
import com.google.firebase.auth.FirebaseAuth

class DetailOrderMenuFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailOrderMenuBinding>()
    private val args by navArgs<DetailOrderMenuFragmentArgs>()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val viewModel: ProfileViewModel by activityViewModels()
    private val uid = firebaseAuth.currentUser?.uid

    companion object {
        const val shipment = 4000
        const val discount = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_order_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedVariant = args.selectedVariant
        val totalPrice = selectedVariant.price + shipment - discount

        binding.apply {
            includeAppBarMiddle.apply {
                tvTittle.text = "Detail Pemesanan"
                ivFavorite.visibility = View.INVISIBLE
            }
            includeSelectedItemVariant.apply {
                tvTittleVariant.text = selectedVariant.variant
                tvSubtitleVariant.text = selectedVariant.composition
                tvVariantPrice.text = Formatting.rupiahCurrencyFormatting(selectedVariant.price)
                Glide.with(requireContext())
                    .load(selectedVariant.image)
                    .centerCrop()
                    .into(ivVariantImg)
            }
            tvIngredientsPrice.text = Formatting.rupiahCurrencyFormatting(selectedVariant.price)
            tvShipmentPrice.text = Formatting.rupiahCurrencyFormatting(shipment)
            tvDiscountPrice.text = String.format(
                "- ${Formatting.rupiahCurrencyFormatting(discount)}"
            )
            tvTotalPaymentPrice.text = Formatting.rupiahCurrencyFormatting(totalPrice)
        }

        uid?.let {
            viewModel.getUser(it).observe(viewLifecycleOwner, observe(totalPrice))
        }

        binding.includeBottomBarDetailOrder.apply {
            Handler(Looper.getMainLooper()).postDelayed({
                btnOrderNow.setOnClickListener {
                    it.findNavController().navigate(
                        DetailOrderMenuFragmentDirections
                            .actionDetailOrderMenuFragmentToDetailOrderVerificationFragment()
                    )
                }
            }, 2500)
        }
    }

    private fun observe(totalPrice: Int): Observer<in User> = Observer {
        binding.tvDetailLocation.text = it.address
        binding.includeBottomBarDetailOrder.includePaymentCalculation.apply {
            tvCWalletValue.text = it.cookiezWallet.toString()
            tvCashValue.text = String.format("${totalPrice - it.cookiezWallet}")
        }
    }
}