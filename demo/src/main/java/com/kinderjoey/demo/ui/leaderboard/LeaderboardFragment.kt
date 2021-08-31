package com.kinderjoey.demo.ui.leaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.demo.R
import com.kinderjoey.demo.data.DataDummy
import com.kinderjoey.demo.databinding.FragmentLeaderboardBinding


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

        leaderboardAdapter.setAllData(DataDummy.setLeaderboard())
    }
}