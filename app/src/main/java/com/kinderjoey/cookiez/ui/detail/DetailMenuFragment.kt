package com.kinderjoey.cookiez.ui.detail

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.DetailMenuPageAdapter
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentDetailMenuBinding
import com.kinderjoey.cookiez.model.Favorite
import com.kinderjoey.cookiez.model.menu.Menu
import com.kinderjoey.cookiez.ui.profile.ProfileViewModel
import com.kinderjoey.cookiez.util.Constanta
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMenuFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailMenuBinding>()
    private var simpleExoPlayer: SimpleExoPlayer? = null
    private var playerView: PlayerView? = null
    private lateinit var mediaDataSourceFactory: DataSource.Factory
    private val viewModel by viewModel<DetailMenuViewModel>()
    private var isFavorite = false
    private var menu: Menu? = null
    private var favorite: Favorite? = null
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val uid = firebaseAuth.currentUser?.uid
    private var id = ""
    private val profileViewModel: ProfileViewModel by viewModels()

    companion object {
        var STREAM_URL = ""
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

        val menuName = activity?.intent?.getStringExtra(Constanta.KEY_OF_MENU_NAME)

        uid?.let {
            profileViewModel.getUser(it).observe(viewLifecycleOwner, { user ->
                id = user.id
                observeDetail(menuName)
            })
        }



        playerView?.setOnClickListener {
            if (simpleExoPlayer?.playWhenReady == true)
                onPause()
            else
                onStart()
        }

        binding.includeAppBarMiddle.apply {
            tvTittle.text = menuName
            ivArrowBack.setOnClickListener {
                activity?.finish()
            }
        }

        binding.includeBottomBarDetail.btnOrder.setOnClickListener {
            view.findNavController().navigate(DetailMenuFragmentDirections
                .actionDetailMenuFragmentToDetailVariantMenuFragment(menuName!!))
        }

        val pagerAdapter = menuName?.let {
            DetailMenuPageAdapter(
                requireActivity().supportFragmentManager,
                lifecycle,
                it
            )
        }

        binding.apply {
            pagerAdapter.apply {
                viewPager.adapter = this
            }
            TabLayoutMediator(tabDetail, viewPager) { tab, position ->
                tab.text = TAB_TITLES[position]
            }.attach()

            includeBottomBarDetail.btnOrder.setOnClickListener {
                view.findNavController().navigate(
                    DetailMenuFragmentDirections
                        .actionDetailMenuFragmentToDetailVariantMenuFragment(menuName!!))
            }
        }

        binding.includeAppBarMiddle.ivFavorite.setOnClickListener {
            if (!isFavorite) {
                viewModel.addToFavorite(id, menu?.title!!, favorite!!).observe(viewLifecycleOwner, { resource ->
                    when(resource) {
                        is Resource.Empty -> {}
                        is Resource.Error -> Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            binding.includeAppBarMiddle.ivFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_true))
                            isFavorite = true
                        }
                    }
                })
            } else {
                viewModel.removeFromFavorite(id, menu?.title!!).observe(viewLifecycleOwner, { resource ->
                    when(resource) {
                        is Resource.Empty -> {}
                        is Resource.Error -> Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            binding.includeAppBarMiddle.ivFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_false))
                            isFavorite = false
                        }
                    }
                })
            }
        }
    }

    private fun initializePlayer(streamUrl: String?) {
        mediaDataSourceFactory = DefaultDataSourceFactory(requireActivity(), Util.getUserAgent(requireActivity(), "mediaPlayerSample"))

        val mediaSource = ProgressiveMediaSource.Factory(mediaDataSourceFactory).createMediaSource(
            MediaItem.fromUri(streamUrl.toString()))

        val mediaSourceFactory: MediaSourceFactory = DefaultMediaSourceFactory(mediaDataSourceFactory)

        simpleExoPlayer = SimpleExoPlayer.Builder(requireActivity())
            .setMediaSourceFactory(mediaSourceFactory)
            .build()

        simpleExoPlayer!!.addMediaSource(mediaSource)

        simpleExoPlayer!!.playWhenReady = true

        binding.playerView.setShutterBackgroundColor(Color.TRANSPARENT)
        binding.playerView.player = simpleExoPlayer
        binding.playerView.requestFocus()
    }

    private fun releasePlayer() {
        simpleExoPlayer?.release()
    }

    private fun observeDetail(menuName: String?) {
        viewModel.getDetailMenu(menuName!!).observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Empty -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    menu = it.data
                    initializePlayer(menu?.videoUrl)
                    observeIsFavorite()
                }
            }
        })
    }

    private fun observeIsFavorite() {
        viewModel.isFavorite(id, menu?.title.toString()).observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Empty -> binding.includeAppBarMiddle.ivFavorite.visibility = View.INVISIBLE
                is Resource.Error -> binding.includeAppBarMiddle.ivFavorite.visibility = View.INVISIBLE
                is Resource.Loading -> binding.includeAppBarMiddle.ivFavorite.visibility = View.INVISIBLE
                is Resource.Success -> {
                    binding.includeAppBarMiddle.ivFavorite.visibility = View.VISIBLE
                    if(it.data == true) {
                        isFavorite = true
                        binding.includeAppBarMiddle.ivFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_true))
                    }
                    else {
                        isFavorite = false
                        binding.includeAppBarMiddle.ivFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_false))
                    }

                    favorite = Favorite(
                        id,
                        menu?.title!!,
                        menu?.time!!,
                        menu?.difficulty!!,
                        menu?.price!!,
                        menu?.rating!!,
                        menu?.image!!,
                        menu?.type!!
                    )
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()

        if (Util.SDK_INT > 23) initializePlayer(menu?.videoUrl)
    }

    override fun onResume() {
        super.onResume()

        if (Util.SDK_INT <= 23) initializePlayer(menu?.videoUrl)
    }

    override fun onPause() {
        super.onPause()

        if (Util.SDK_INT <= 23) releasePlayer()
    }

    override fun onStop() {
        super.onStop()

        if (Util.SDK_INT > 23) releasePlayer()
    }
}