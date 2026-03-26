package com.umc.workbook.ui.wish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.umc.workbook.R
import com.umc.workbook.databinding.FragmentWishlistBinding
import com.umc.workbook.model.PurchaseProductItem
import com.umc.workbook.ui.purchase.adapter.PurchaseProductAdapter

class WishlistFragment : Fragment() {

    private lateinit var binding: FragmentWishlistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishlistBinding.inflate(inflater, container, false)
        initWishlistRecyclerView()
        return binding.root
    }

    private fun initWishlistRecyclerView() {
        val items = listOf(
            PurchaseProductItem(
                imageResId = R.drawable.img_sock,
                badge = false,
                isWish = true,
                name = "Air Jordan 1 Mid",
                desc = "",
                colors = "",
                price = "US$125"
            ),
            PurchaseProductItem(
                imageResId = R.drawable.img_shoes_2,
                badge = false,
                isWish = true,
                name = "Nike Everyday Plus Cushioned",
                desc = "Training Ankle Socks (6 Pairs)",
                colors = "5 Colours",
                price = "US$10"
            ),
            PurchaseProductItem(
                imageResId = R.drawable.img_shoes_3,
                badge = true,
                isWish = true,
                name = "Nike Air Force 1 '07",
                desc = "Women's Shoes",
                colors = "5 Colours",
                price = "US$115"
            ),
            PurchaseProductItem(
                imageResId = R.drawable.img_shoes_4,
                badge = true,
                isWish = true,
                name = "Jordan Essentials",
                desc = "Men's Shoes",
                colors = "2 Colours",
                price = "US$115"
            )
        )

        binding.recyclerWishlist.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = PurchaseProductAdapter(items)
        }
    }
}
