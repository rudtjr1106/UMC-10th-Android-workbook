package com.umc.workbook.ui.purchase.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.workbook.databinding.FragmentPurchaseEmptyBinding

class PurchaseSaleFragment : Fragment() {

    private lateinit var binding: FragmentPurchaseEmptyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPurchaseEmptyBinding.inflate(inflater, container, false)
        return binding.root
    }
}