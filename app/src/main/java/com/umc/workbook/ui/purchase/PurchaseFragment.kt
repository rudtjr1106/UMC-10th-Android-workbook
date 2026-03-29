package com.umc.workbook.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.umc.workbook.R
import com.umc.workbook.databinding.FragmentPurchaseBinding
import com.umc.workbook.model.PurchaseTab

class PurchaseFragment : Fragment() {

    private lateinit var binding: FragmentPurchaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        initTabClicks()
        renderTab(PurchaseTab.SHOES)
        return binding.root
    }

    private fun initTabClicks() {
        binding.textAll.setOnClickListener { navigateTab(PurchaseTab.SHOES) }
        binding.textTops.setOnClickListener { navigateTab(PurchaseTab.TOPS) }
        binding.textSale.setOnClickListener { navigateTab(PurchaseTab.SALE) }
    }

    private fun navigateTab(tab: PurchaseTab) {
        val navHost = childFragmentManager.findFragmentById(R.id.purchase_tab_nav_host) as? NavHostFragment
            ?: return
        val navController = navHost.navController
        val destinationId = when (tab) {
            PurchaseTab.SHOES -> R.id.purchaseShoesFragment
            PurchaseTab.TOPS -> R.id.purchaseTopsFragment
            PurchaseTab.SALE -> R.id.purchaseSaleFragment
        }

        if (navController.currentDestination?.id != destinationId) {
            navController.navigate(destinationId)
        }
        renderTab(tab)
    }

    private fun renderTab(tab: PurchaseTab) {
        val activeColor = ContextCompat.getColor(requireContext(), R.color.black)
        val inactiveColor = ContextCompat.getColor(requireContext(), R.color.gray100)
        val colorByTab = mapOf(
            PurchaseTab.SHOES to if (tab == PurchaseTab.SHOES) activeColor else inactiveColor,
            PurchaseTab.TOPS to if (tab == PurchaseTab.TOPS) activeColor else inactiveColor,
            PurchaseTab.SALE to if (tab == PurchaseTab.SALE) activeColor else inactiveColor
        )

        binding.textAll.setTextColor(colorByTab.getValue(PurchaseTab.SHOES))
        binding.textTops.setTextColor(colorByTab.getValue(PurchaseTab.TOPS))
        binding.textSale.setTextColor(colorByTab.getValue(PurchaseTab.SALE))
    }
}
