package com.kinderjoey.cookiez.ui.leaderboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.LeaderboardAdapter
import com.kinderjoey.cookiez.databinding.FragmentLeaderboardBinding
import com.kinderjoey.cookiez.model.UserLeaderBoard
import kotlinx.android.synthetic.main.dialog_rank.view.*
import kotlinx.android.synthetic.main.dialog_voucher.view.btn_ok
import kotlinx.android.synthetic.main.dialog_voucher.view.ib_close


class LeaderboardFragment : Fragment() {

    private val binding by viewBinding<FragmentLeaderboardBinding>()
    private val viewModel: LeaderboardViewModel by activityViewModels()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val uid = firebaseAuth.currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (uid != null) {
            viewModel.getLeaderboard(uid).first.observe(viewLifecycleOwner, ::setLeaderBoard)
            viewModel.getLeaderboard(uid).second.observe(viewLifecycleOwner, ::setDialog)

        }


    }

    @SuppressLint("SetTextI18n")
    private fun setDialog(user: UserLeaderBoard) {

        binding.tvMyRank.setOnClickListener {
            val materialBuilder = MaterialAlertDialogBuilder(requireContext()).create()
            val inflater = layoutInflater.inflate(R.layout.dialog_rank, null)
            with(inflater) {

                val imageAvatar =
                    if (user.avatar.isNotEmpty()) user.avatar else R.drawable.ic_ava

                Glide.with(context)
                    .load(imageAvatar)
                    .placeholder(R.drawable.no_image)
                    .into(iv_profile)


                tv_xp.text = "${user.xp} XP"

                val rankDescrption = getString(R.string.desc_dialog_rank, user.rank)

                tv_desc_rank.text = rankDescrption
                btn_ok.setOnClickListener {
                    materialBuilder.dismiss()
                }
                ib_close.setOnClickListener {
                    materialBuilder.dismiss()
                }
                materialBuilder.setView(inflater)
                materialBuilder.show()
            }


        }

    }

    private fun setLeaderBoard(listItem: ArrayList<UserLeaderBoard>) {

        binding.rvLeaderboard.apply {
            val leaderboardAdapter = LeaderboardAdapter()
            leaderboardAdapter.setAllData(listItem)
            adapter = leaderboardAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }
}