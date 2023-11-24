package com.example.hotelbooking.adapters.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapter
import com.example.hotelbooking.databinding.ItemPagerImageBinding

class SimpleImageAdapterDelegate(val glide : RequestManager) : DelegateAdapter<SimpleImageItem, SimpleImageAdapterDelegate.ImageHolder>(SimpleImageItem::class.java) {


    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ImageHolder(
            ItemPagerImageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun bindViewHolder(model: SimpleImageItem, viewHolder: ImageHolder) {
        viewHolder.bind(model)
    }

    inner class ImageHolder(private val binding: ItemPagerImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SimpleImageItem) {
            glide
                .load(item.url)
                .into(binding.imageView)

        }
    }
}