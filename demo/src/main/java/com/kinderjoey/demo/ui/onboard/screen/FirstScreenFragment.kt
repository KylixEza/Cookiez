package com.kinderjoey.demo.ui.onboard.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.viewpager2.widget.ViewPager2
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentFirstScreenBinding

class FirstScreenFragment : Fragment() {

    private val firstScreenBinding by viewBinding<FragmentFirstScreenBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.statusBarColor = View.GONE
        return inflater.inflate(R.layout.fragment_first_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        firstScreenBinding.btnNext.setOnClickListener {
            pager?.currentItem = 1
        }
    }
}