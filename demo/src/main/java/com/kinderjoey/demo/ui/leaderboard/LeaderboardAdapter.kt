package com.kinderjoey.demo.ui.leaderboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinderjoey.demo.databinding.ItemListLeaderboardBinding

class LeaderboardAdapter: RecyclerView.Adapter<LeaderboardAdapter.LeaderBoardViewHolder>() {

    private val listOfLeaderboard = ArrayList<Int>()

    fun setAllData(data: List<Int>) {
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
        holder.bind(listOfLeaderboard[position])
    }

    override fun getItemCount(): Int = listOfLeaderboard.size

    class LeaderBoardViewHolder(val view: ItemListLeaderboardBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(leaderboard: Int) {
            Glide.with(itemView.context)
                .load(leaderboard)
                .into(view.ivLeaderboard)
        }

    }
}