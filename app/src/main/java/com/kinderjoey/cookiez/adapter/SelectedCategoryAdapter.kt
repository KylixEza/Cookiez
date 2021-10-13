package com.kinderjoey.cookiez.adapter

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ItemListMenuBinding
import com.kinderjoey.cookiez.model.menu.Menu
import com.kinderjoey.cookiez.ui.detail.DetailActivity
import com.kinderjoey.cookiez.util.Constanta


class SelectedCategoryAdapter: RecyclerView.Adapter<SelectedCategoryAdapter.SelectedCategoryViewHolder>() {

    private val listOfMenu = ArrayList<Menu>()

    fun setAllData(data: List<Menu>) {
        listOfMenu.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedCategoryAdapter.SelectedCategoryViewHolder {
        val view = ItemListMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectedCategoryViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holderSelected: SelectedCategoryAdapter.SelectedCategoryViewHolder, position: Int) {
        holderSelected.bind(listOfMenu[position])
    }

    override fun getItemCount(): Int = listOfMenu.size

    inner class SelectedCategoryViewHolder(private val view: ItemListMenuBinding): RecyclerView.ViewHolder(view.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(menu: Menu) {
            view.apply {
                Glide.with(itemView.context)
                    .load(menu.image)
                    .centerCrop()
                    .into(ivImgMenu)
                tvRating.text = menu.rating.toString()
                tvTitle.text = menu.title
                tvEstimatedTime.text = String.format("${menu.time} Menit")

                when (menu.difficulty) {
                    "Mudah" -> {
                        tvDifficulty.apply {
                            text = menu.difficulty
                            setTextColor(itemView.context.getColor(R.color.text_difficult_easy))
                        }
                        cvDifficulty.setCardBackgroundColor(itemView.context.getColor(R.color.card_difficult_easy))
                    }
                    "Menengah" -> {
                        tvDifficulty.apply {
                            text = menu.difficulty
                            setTextColor(itemView.context.getColor(R.color.text_difficult_medium))
                        }
                        cvDifficulty.setCardBackgroundColor(itemView.context.getColor(R.color.card_difficult_medium))
                    }
                    "Sulit" -> {
                        tvDifficulty.apply {
                            text = menu.difficulty
                            setTextColor(itemView.context.getColor(R.color.text_difficult_hard))
                        }
                        cvDifficulty.setCardBackgroundColor(itemView.context.getColor(R.color.card_difficult_hard))
                    }
                }
                tvPrice.text = String.format("Rp ${menu.price}")
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(Constanta.KEY_OF_MENU_NAME, menu.title)
                itemView.context.startActivity(intent)
            }
        }

    }
}