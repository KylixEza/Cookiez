package com.kinderjoey.cookiez.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ItemListLeaderboardBinding
import com.kinderjoey.cookiez.model.UserLeaderBoard


class LeaderboardAdapter: RecyclerView.Adapter<LeaderboardAdapter.LeaderBoardViewHolder>() {

    private val listOfLeaderboard = ArrayList<UserLeaderBoard>()

    fun setAllData(data: List<UserLeaderBoard>) {
        listOfLeaderboard.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LeaderboardAdapter.LeaderBoardViewHolder {
        val view = ItemListLeaderboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeaderBoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderboardAdapter.LeaderBoardViewHolder, position: Int) {
        holder.bind(listOfLeaderboard[position], position)
    }

    override fun getItemCount(): Int = listOfLeaderboard.size

    class LeaderBoardViewHolder(val view: ItemListLeaderboardBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(leaderboard: UserLeaderBoard, position: Int) {
            val rank = position + 1
            when (rank) {
                1 -> {
                    view.apply {
                        tvRank.visibility = View.INVISIBLE
                        ivRank.setImageResource(R.drawable.ic_rank_first)
                        Glide.with(itemView.context)
                            .load(leaderboard.avatar)
                            .placeholder(R.drawable.no_image)
                            .into(ivAvatar)
                        tvAccountName.text = leaderboard.username
                        tvXpPoints.text = String.format("${leaderboard.xp} XP")
                    }
                }
                2 -> {
                    view.apply {
                        tvRank.visibility = View.INVISIBLE
                        ivRank.setImageResource(R.drawable.ic_rank_second)
                        Glide.with(itemView.context)
                            .load(leaderboard.avatar)
                            .placeholder(R.drawable.no_image)
                            .into(ivAvatar)
                        tvAccountName.text = leaderboard.username
                        tvXpPoints.text = String.format("${leaderboard.xp} XP")
                    }
                }
                3 -> {
                    view.apply {
                        tvRank.visibility = View.INVISIBLE
                        ivRank.setImageResource(R.drawable.ic_rank_third)
                        Glide.with(itemView.context)
                            .load(leaderboard.avatar)
                            .placeholder(R.drawable.no_image)
                            .into(ivAvatar)
                        tvAccountName.text = leaderboard.username
                        tvXpPoints.text = String.format("${leaderboard.xp} XP")
                    }
                }
                else -> {
                    view.apply {
                        tvRank.text = leaderboard.rank.toString()
                        ivRank.visibility = View.INVISIBLE
                        Glide.with(itemView.context)
                            .load(leaderboard.avatar)
                            .placeholder(R.drawable.no_image)
                            .into(ivAvatar)
                        tvAccountName.text = leaderboard.username
                        tvXpPoints.text = String.format("${leaderboard.xp} XP")
                    }
                }
            }
        }
    }
}