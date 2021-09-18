package com.kinderjoey.cookiez.ui.leaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.LeaderboardAdapter
import com.kinderjoey.cookiez.databinding.FragmentLeaderboardBinding


class LeaderboardFragment : Fragment() {

    private val binding by viewBinding<FragmentLeaderboardBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leaderboardAdapter = LeaderboardAdapter()

        binding.rvLeaderboard.apply {
            adapter = leaderboardAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        //leaderboardAdapter.setAllData(DataDummy.setLeaderboard())
    }
}