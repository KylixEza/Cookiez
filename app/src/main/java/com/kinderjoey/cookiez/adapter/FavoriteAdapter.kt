package com.kinderjoey.cookiez.adapter

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ItemListFavoriteBinding
import com.kinderjoey.cookiez.model.Favorite
import com.kinderjoey.cookiez.ui.detail.DetailActivity
import com.kinderjoey.cookiez.util.Constanta
import com.kinderjoey.cookiez.util.Formatting

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val listOfFavorite = ArrayList<Favorite>()

    fun setAllData(data: List<Favorite>) {
        listOfFavorite.apply {
            clear()
            addAll(data)
        }
        notifyItemRangeRemoved(0, listOfFavorite.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = ItemListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listOfFavorite[position])
    }

    override fun getItemCount(): Int = listOfFavorite.size

    inner class FavoriteViewHolder(private val view: ItemListFavoriteBinding): RecyclerView.ViewHolder(view.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(favorite: Favorite) {
            view.apply {
                Glide.with(itemView.context)
                    .load(favorite.image)
                    .centerCrop()
                    .into(ivImgFavorite)
                tvRatingFavorite.text = favorite.rating.toString()
                tvTitleFavorite.text = favorite.title
                tvEstimatedTimeFavorite.text = String.format("${favorite.time} Menit")

                when (favorite.difficulty) {
                    "Mudah" -> {
                        tvDifficultyFavorite.apply {
                            text = favorite.difficulty
                            setTextColor(itemView.context.getColor(R.color.text_difficult_easy))
                        }
                        cvDifficulty.setCardBackgroundColor(itemView.context.getColor(R.color.card_difficult_easy))
                    }
                    "Menengah" -> {
                        tvDifficultyFavorite.apply {
                            text = favorite.difficulty
                            setTextColor(itemView.context.getColor(R.color.text_difficult_medium))
                        }
                        cvDifficulty.setCardBackgroundColor(itemView.context.getColor(R.color.card_difficult_medium))
                    }
                    "Sulit" -> {
                        tvDifficultyFavorite.apply {
                            text = favorite.difficulty
                            setTextColor(itemView.context.getColor(R.color.text_difficult_hard))
                        }
                        cvDifficulty.setCardBackgroundColor(itemView.context.getColor(R.color.card_difficult_hard))
                    }
                }
                tvPriceFavorite.text = String.format("Rp ${Formatting.rupiahCurrencyFormatting(favorite.price)}")
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(Constanta.KEY_OF_MENU_NAME, favorite.title)
                itemView.context.startActivity(intent)
            }
        }
    }
}