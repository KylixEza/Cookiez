package com.kinderjoey.demo.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.commit
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.Util
import com.google.android.material.tabs.TabLayoutMediator
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentDetailMenuBinding
import com.kinderjoey.demo.ui.detail.order.DetailVariantMenuFragment
import com.kinderjoey.demo.ui.detail.screen.DetailMenuAboutFragment
import com.kinderjoey.demo.ui.detail.screen.DetailMenuReviewFragment
import com.kinderjoey.demo.ui.detail.screen.DetailMenuTutorialFragment
import com.kinderjoey.demo.ui.detail.screen.adapter.DetailMenuPageAdapter
import com.kinderjoey.demo.ui.voucher.VoucherFragment

class DetailMenuFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailMenuBinding>()
    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    companion object {
        const val STREAM_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4z"
        val TAB_TITLES = listOf(
            "Petunjuk",
            "Tentang",
            "Ulasan"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializePlayer()

        val fragmentManager = parentFragmentManager
        val fragment = DetailVariantMenuFragment()

        binding.includeBottomBarDetail.btnOrder.setOnClickListener {
            fragmentManager.commit {
                replace(R.id.detail_container, fragment, fragment::class.java.simpleName)
            }
        }

        val pagerAdapter = DetailMenuPageAdapter(
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val listOfFragment = listOf<Fragment>(
            DetailMenuTutorialFragment(),
            DetailMenuAboutFragment(),
            DetailMenuReviewFragment()
        )

        binding.apply {
            pagerAdapter.apply {
                setFragments(listOfFragment)
                viewPager.adapter = this
            }
            TabLayoutMediator(tabDetail, viewPager) { tab, position ->
                tab.text = TAB_TITLES[position]
            }.attach()
        }
    }

    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(requireActivity())
            .build()
            .also { exoPlayer ->
                binding.playerView.player = exoPlayer
                val mediaItem = MediaItem.fromUri(STREAM_URL)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                exoPlayer.prepare()
            }

    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT < 24)) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }


    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            playWhenReady = this.playWhenReady
            release()
        }
        player = null
    }

}