package com.kinderjoey.demo.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.commit
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentDetailMenuBinding
import com.kinderjoey.demo.ui.detail.order.DetailVariantMenuFragment

class DetailMenuFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailMenuBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager = parentFragmentManager
        val fragment = DetailVariantMenuFragment()

        binding.includeBottomBarDetail.btnOrder.setOnClickListener {
            fragmentManager.commit {
                replace(R.id.detail_container, fragment, fragment::class.java.simpleName)
            }
        }
    }

}