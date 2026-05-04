package com.umc.workbook.ui.purchase.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.umc.workbook.databinding.FragmentPurchaseShoesBinding
import com.umc.workbook.model.PurchaseProductItem
import com.umc.workbook.ui.purchase.adapter.PurchaseProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PurchaseShoesFragment : Fragment() {

    private lateinit var binding: FragmentPurchaseShoesBinding
    private val viewModel: PurchaseShoesViewModel by viewModels()

    private val purchaseProductAdapter: PurchaseProductAdapter by lazy {
        PurchaseProductAdapter(object : PurchaseProductAdapter.Delegate {
            override fun onWishClick(item: PurchaseProductItem) {
                viewModel.togglePurchaseWish(item)
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
        observeViewModel()
        viewModel.loadPurchaseItems()
        return binding.root
    }

    private fun initPurchaseRecyclerView() {
        binding.recyclerPurchase.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = purchaseProductAdapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    purchaseProductAdapter.submitItems(uiState.purchaseItems)
                }
            }
        }
    }
}
