package com.umc.workbook.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.umc.workbook.databinding.ItemHomeShoeBinding
import com.umc.workbook.model.HomeShoeItem

class HomeShoesViewHolder(
    private val binding: ItemHomeShoeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HomeShoeItem) {
        binding.apply {
            imageShoe.setImageResource(item.imageResId)
            textName.text = item.name
            textPrice.text = item.price
        }
    }
}