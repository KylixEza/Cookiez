package com.kinderjoey.cookiez.ui.detail.order

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentDetailOrderVerificationBinding
import com.kinderjoey.cookiez.ui.history.HistoryActivity

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

        binding.btnGoToHistory.setOnClickListener {
            val intent = Intent(activity, HistoryActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}