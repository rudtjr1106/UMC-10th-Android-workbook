package com.umc.workbook.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.umc.workbook.R
import com.umc.workbook.databinding.FragmentPurchaseBinding
import com.umc.workbook.model.PurchaseProductItem
import com.umc.workbook.ui.purchase.adapter.PurchaseProductAdapter

class PurchaseFragment : Fragment() {

    private lateinit var binding: FragmentPurchaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        initPurchaseRecyclerView()
        return binding.root
    }

    private fun initPurchaseRecyclerView() {
        val items = listOf(
            PurchaseProductItem(
                imageResId = R.drawable.img_sock,
                badge = false,
                isWish = true,
                name = "Nike Everyday Plus",
                desc = "Training Ankle Socks (6 Pairs)",
                colors = "5 Colours",
                price = "US$10"
            ),
            PurchaseProductItem(
                imageResId = R.drawable.img_sock,
                badge = false,
                isWish = false,
                name = "Nike Elite Crew",
                desc = "Basketball Socks",
                colors = "7 Colours",
                price = "US$16"
            ),
            PurchaseProductItem(
                imageResId = R.drawable.img_shoes_2,
                badge = true,
                isWish = false,
                name = "Nike Air Force 1 '07",
                desc = "Women's Shoes",
                colors = "5 Colours",
                price = "US$115"
            ),
            PurchaseProductItem(
                imageResId = R.drawable.img_shoes_3,
                badge = true,
                isWish = false,
                name = "Jordan ENike Air Force 1 '07ssentials",
                desc = "Men's Shoes",
                colors = "2 Colours",
                price = "US$115"
            )
        )

        binding.recyclerPurchase.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = PurchaseProductAdapter(items)
        }
    }
}
