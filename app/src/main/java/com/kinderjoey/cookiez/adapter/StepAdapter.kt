package com.kinderjoey.cookiez.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kinderjoey.cookiez.databinding.ItemListStepBinding

class StepAdapter: RecyclerView.Adapter<StepAdapter.StepViewHolder>() {

    private val listOfSteps = ArrayList<String>()

    fun setAllData(data: List<String>) {
        listOfSteps.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val view = ItemListStepBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StepViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.bind(listOfSteps[position], position)
    }

    override fun getItemCount(): Int = listOfSteps.size

    inner class StepViewHolder(private val view: ItemListStepBinding): RecyclerView.ViewHolder(view.root) {

        fun bind(step: String, position: Int) {
            view.apply {
                tvOrderedNumber.text = position.plus(1).toString()
                tvIngredient.text = step
            }
        }
    }
}