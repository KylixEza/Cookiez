package com.kinderjoey.cookiez.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kinderjoey.cookiez.databinding.ItemListReviewBinding
import com.kinderjoey.cookiez.model.menu.Review

class ReviewAdapter: RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private val listOfReview = ArrayList<Review>()

    fun setAllData(data: List<Review>) {
        listOfReview.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class ReviewViewHolder(val view: ItemListReviewBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(review: Review) {
            view.apply {
                Glide.with(itemView.context)
                    .load(review.imageReviewer)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivImageReviewer)

                tvNameReviewer.text = review.nameReviewer
                ratingBar.rating = review.starReviewer.toFloat()
                tvRating.text = review.starReviewer.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = ItemListReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(listOfReview[position])
    }

    override fun getItemCount(): Int = listOfReview.size
}