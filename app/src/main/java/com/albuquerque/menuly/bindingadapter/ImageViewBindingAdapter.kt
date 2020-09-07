package com.albuquerque.menuly.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.albuquerque.menuly.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

@BindingAdapter("src")
fun loadImage(imageView: ImageView, src: String?) {

    Glide
            .with(imageView.context)
            .load(src)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_placeholder_food)
            .transform(CenterCrop(), RoundedCorners(16))
            .into(imageView)

}