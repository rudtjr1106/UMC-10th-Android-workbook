package com.umc.workbook.ui.bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.workbook.MainActivity
import com.umc.workbook.R
import com.umc.workbook.databinding.FragmentBagBinding

class BagFragment : Fragment() {

    private lateinit var binding: FragmentBagBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBagBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOrder.setOnClickListener {
            (activity as? MainActivity)?.navigateToBottomTab(R.id.nav_purchase)
        }
    }
}
