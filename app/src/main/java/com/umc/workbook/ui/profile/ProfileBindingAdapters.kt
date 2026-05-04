package com.umc.workbook.ui.profile

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation

@BindingAdapter("profileAvatarUrl")
fun ImageView.bindProfileAvatarUrl(avatarUrl: String?) {
    if (avatarUrl.isNullOrBlank()) return

    load(avatarUrl) {
        transformations(CircleCropTransformation())
    }
}
