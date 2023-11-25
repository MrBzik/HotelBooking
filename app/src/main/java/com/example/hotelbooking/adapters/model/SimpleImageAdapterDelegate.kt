package com.example.hotelbooking.adapters.model

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.hotelbooking.R
import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapter
import com.example.hotelbooking.databinding.ItemPagerImageBinding
import com.example.hotelbooking.utils.Logger

class SimpleImageAdapterDelegate(
    val glide : RequestManager,
    val imageLoadedCallback : () -> Unit = {}
) : DelegateAdapter<SimpleImageItem, SimpleImageAdapterDelegate.ImageHolder>(SimpleImageItem::class.java) {

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

            Logger.log("BIND?")

            glide
                .load(item.url)
                .addListener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        Logger.log("LOADED")
                        imageLoadedCallback()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        Logger.log("LOADED")
                        imageLoadedCallback()
                        return false
                    }
                })
                .placeholder(R.drawable.bg_shimmer)
                .into(binding.imageView)

        }
    }
}