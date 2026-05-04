package com.umc.workbook.ui.wish

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
import com.umc.workbook.databinding.FragmentWishlistBinding
import com.umc.workbook.model.PurchaseProductItem
import com.umc.workbook.ui.purchase.adapter.PurchaseProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishlistFragment : Fragment() {

    private lateinit var binding: FragmentWishlistBinding
    private val viewModel: WishlistViewModel by viewModels()

    private val wishlistAdapter: PurchaseProductAdapter by lazy {
        PurchaseProductAdapter(object : PurchaseProductAdapter.Delegate {
            override fun onWishClick(item: PurchaseProductItem) = Unit
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishlistBinding.inflate(inflater, container, false)
        initWishlistRecyclerView()
        observeViewModel()
        viewModel.loadWishlistItems()
        return binding.root
    }

    private fun initWishlistRecyclerView() {
        binding.recyclerWishlist.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = wishlistAdapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    wishlistAdapter.submitItems(uiState.wishlistItems)
                }
            }
        }
    }
}
