package com.umc.workbook.ui.purchase.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.umc.workbook.data.AppDataStore
import com.umc.workbook.databinding.FragmentPurchaseShoesBinding
import com.umc.workbook.model.PurchaseProductItem
import com.umc.workbook.ui.purchase.adapter.PurchaseProductAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PurchaseShoesFragment : Fragment() {

    private lateinit var binding: FragmentPurchaseShoesBinding

    private val purchaseProductAdapter: PurchaseProductAdapter by lazy {
        PurchaseProductAdapter(object : PurchaseProductAdapter.Delegate {
            override fun onWishClick(item: PurchaseProductItem) {
                viewLifecycleOwner.lifecycleScope.launch {
                    AppDataStore.togglePurchaseWish(requireContext(), item)
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPurchaseShoesBinding.inflate(inflater, container, false)
        initPurchaseRecyclerView()
        return binding.root
    }

    private fun initPurchaseRecyclerView() {
        binding.recyclerPurchase.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = purchaseProductAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            AppDataStore.purchaseItemsFlow(requireContext()).collectLatest { items ->
                purchaseProductAdapter.submitItems(items)
            }
        }
    }
}