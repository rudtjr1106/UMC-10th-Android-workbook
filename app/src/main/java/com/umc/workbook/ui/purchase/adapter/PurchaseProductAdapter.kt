package com.umc.workbook.ui.purchase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.workbook.databinding.ItemPurchaseProductBinding
import com.umc.workbook.model.PurchaseProductItem

class PurchaseProductAdapter(
    private val delegate: Delegate
) : RecyclerView.Adapter<PurchaseProductViewHolder>() {

    private val items = mutableListOf<PurchaseProductItem>()

    interface Delegate {
        fun onWishClick(item: PurchaseProductItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseProductViewHolder {
        val binding = ItemPurchaseProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PurchaseProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PurchaseProductViewHolder, position: Int) {
        holder.bind(items[position], delegate)
    }

    override fun getItemCount(): Int = items.size

    fun submitItems(newItems: List<PurchaseProductItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
