package com.kinderjoey.cookiez.ui.detail.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentDetailMenuTutorialBinding

class DetailMenuTutorialFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailMenuTutorialBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_menu_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}