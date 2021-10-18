package com.kinderjoey.cookiez.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.FavoriteAdapter
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentFavoriteBinding
import com.kinderjoey.cookiez.ui.profile.ProfileViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()
    private val binding by viewBinding<FragmentFavoriteBinding>()
    private lateinit var favoriteAdapter: FavoriteAdapter
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val uid = firebaseAuth.currentUser?.uid
    private var id = ""
    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteAdapter = FavoriteAdapter()

        binding.apply {
            includeAppBarFavorite.apply {
                tvTittle.text = "Menu Favorit"
                ivFavorite.visibility = View.INVISIBLE
            }
            rvFavorite.apply {
                adapter = favoriteAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }

        uid?.let {
            profileViewModel.getUser(it).observe(viewLifecycleOwner, { user ->
                id = user.id
                observeListFavorite()
            })
        }
    }

    private fun observeListFavorite() {
        viewModel.getAllFavorites(id).observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Empty -> {
                    binding.progressFavorite.visibility = View.INVISIBLE
                    binding.includeEmptyFavorite.apply {
                        ivImgEmptyFavorite.visibility = View.VISIBLE
                        tvErrorFavorite.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    binding.progressFavorite.visibility = View.INVISIBLE
                    binding.includeEmptyFavorite.apply {
                        ivImgEmptyFavorite.visibility = View.VISIBLE
                        tvErrorFavorite.apply {
                            visibility = View.VISIBLE
                            text = it.message
                        }
                    }
                }
                is Resource.Loading -> binding.progressFavorite.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressFavorite.visibility = View.INVISIBLE
                    it.data?.let { data
                        -> favoriteAdapter.setAllData(data) }
                }
            }
        })
    }

}