package com.umc.workbook.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.workbook.databinding.FragmentPurchaseBinding

class PurchaseFragment : Fragment() {

    private lateinit var binding: FragmentPurchaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        return binding.root
    }
}