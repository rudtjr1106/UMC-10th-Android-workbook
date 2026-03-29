package com.umc.workbook.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.workbook.data.AppDataStore
import com.umc.workbook.databinding.FragmentHomeBinding
import com.umc.workbook.ui.home.adapter.HomeShoesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initShoesRecyclerView()
        return binding.root
    }

    private fun initShoesRecyclerView() {
        binding.recyclerShoes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewLifecycleOwner.lifecycleScope.launch {
            AppDataStore.seedIfEmpty(requireContext())
            AppDataStore.homeItemsFlow(requireContext()).collectLatest { shoes ->
                binding.recyclerShoes.adapter = HomeShoesAdapter(shoes)
            }
        }
    }
}
