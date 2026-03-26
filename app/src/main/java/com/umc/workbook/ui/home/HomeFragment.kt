package com.umc.workbook.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.workbook.R
import com.umc.workbook.databinding.FragmentHomeBinding
import com.umc.workbook.model.HomeShoeItem
import com.umc.workbook.ui.home.adapter.HomeShoesAdapter

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
        val shoes = listOf(
            HomeShoeItem(R.drawable.img_shoes_2, "Air Jordan XXVI", "US$185"),
            HomeShoeItem(R.drawable.img_shoes_3, "Nike Dunk Low", "US$170"),
            HomeShoeItem(R.drawable.img_shoes_4, "Nike Air Max", "US$190")
        )

        binding.recyclerShoes.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = HomeShoesAdapter(shoes)
        }
    }
}
