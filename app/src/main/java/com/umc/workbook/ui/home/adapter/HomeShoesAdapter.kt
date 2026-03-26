package com.umc.workbook.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.workbook.databinding.ItemHomeShoeBinding
import com.umc.workbook.model.HomeShoeItem

class HomeShoesAdapter(
    private val items: List<HomeShoeItem>
) : RecyclerView.Adapter<HomeShoesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeShoesViewHolder {
        val binding = ItemHomeShoeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeShoesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeShoesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
