package com.umc.workbook.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.workbook.model.ReqResUser
import com.umc.workbook.databinding.ItemProfileFollowingBinding

class ProfileFollowingAdapter(
    private var items: List<ReqResUser> = emptyList()
) : RecyclerView.Adapter<ProfileFollowingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileFollowingViewHolder {
        val binding = ItemProfileFollowingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProfileFollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileFollowingViewHolder, position: Int) {
        holder.bind(items[position], position == items.lastIndex)
    }

    override fun getItemCount(): Int = items.size

    fun submitList(users: List<ReqResUser>) {
        items = users
        notifyDataSetChanged()
    }
}
