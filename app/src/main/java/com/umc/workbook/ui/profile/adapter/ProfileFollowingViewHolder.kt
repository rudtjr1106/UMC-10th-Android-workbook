package com.umc.workbook.ui.profile.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.umc.workbook.databinding.ItemProfileFollowingBinding
import com.umc.workbook.data.model.ReqResUser

class ProfileFollowingViewHolder(
    private val binding: ItemProfileFollowingBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: ReqResUser, isLastItem: Boolean) {
        val params = binding.imageFollowingAvatar.layoutParams as ViewGroup.MarginLayoutParams
        params.marginEnd = if (isLastItem) 0 else 6.dpToPx(binding.root.context)
        binding.imageFollowingAvatar.layoutParams = params
        binding.imageFollowingAvatar.load(user.avatar)
    }

    private fun Int.dpToPx(context: android.content.Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }
}