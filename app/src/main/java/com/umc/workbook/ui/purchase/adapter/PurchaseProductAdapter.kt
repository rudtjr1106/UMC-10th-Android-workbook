package com.umc.workbook.ui.purchase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.umc.workbook.databinding.ItemPurchaseProductBinding
import com.umc.workbook.model.PurchaseProductItem

class PurchaseProductAdapter(
    private val items: List<PurchaseProductItem>
) : RecyclerView.Adapter<PurchaseProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseProductViewHolder {
        val binding = ItemPurchaseProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PurchaseProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PurchaseProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
