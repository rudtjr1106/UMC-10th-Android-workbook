package com.umc.workbook.ui.purchase.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.umc.workbook.databinding.ItemPurchaseProductBinding
import com.umc.workbook.model.PurchaseProductItem

class PurchaseProductViewHolder(
    private val binding: ItemPurchaseProductBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PurchaseProductItem, delegate: PurchaseProductAdapter.Delegate) {
        binding.apply {
            imageProduct.setImageResource(item.imageResId)
            textBadge.text = if (item.badge) "Best Seller" else ""
            textBadge.isVisible = item.badge
            imageWish.setImageResource(
                if (item.isWish) com.umc.workbook.R.drawable.ic_heart_circle_active
                else com.umc.workbook.R.drawable.ic_heart_circle
            )
            imageWish.setOnClickListener { delegate.onWishClick(item) }
            textName.text = item.name
            textDesc.text = item.desc
            textColors.text = item.colors
            textPrice.text = item.price
        }
    }
}