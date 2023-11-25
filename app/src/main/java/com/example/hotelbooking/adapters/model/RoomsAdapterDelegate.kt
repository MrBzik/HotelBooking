package com.example.hotelbooking.adapters.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.RequestManager
import com.example.hotelbooking.R
import com.example.hotelbooking.adapters.MainDelegateAdapter
import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapter
import com.example.hotelbooking.databinding.ItemSuitBinding
import com.google.android.material.tabs.TabLayoutMediator

class RoomsAdapterDelegate (
    private val glide : RequestManager,
    private val onClickToNavigate : () -> Unit
) : DelegateAdapter<RoomItem, RoomsAdapterDelegate.RoomHolder>(RoomItem::class.java) {

    class RoomHolder(
        private val bind : ItemSuitBinding,
        private val glide: RequestManager,
        private val onClickToNavigate: () -> Unit
    ) : RecyclerView.ViewHolder(bind.root){

        fun onBind(room : RoomItem){

            bind.tvMainTitle.text = room.room.name
            bind.tvPrice.text = bind.root.resources.getString(R.string.text_price_short, room.room.price.toString())
            bind.tvPriceCondition.text = room.room.pricePer

            val viewPager = MainDelegateAdapter.Builder()
                .add(SimpleImageAdapterDelegate(glide))
                .build()

            viewPager.submitList(room.room.imageUrls.map { SimpleImageItem(it)})

            bind.viewPager.viewPager.adapter = viewPager
            TabLayoutMediator(bind.viewPager.tabLayout, bind.viewPager.viewPager, true){ _, _ ->
            }.attach()

            val peculiaritiesAdapter = MainDelegateAdapter.Builder()
                .add(SimpleTextAdapterDelegate())
                .build()

            peculiaritiesAdapter.submitList(room.room.peculiarities.map { SimpleTextItem(it) })

            bind.rvPeculiarities.adapter = peculiaritiesAdapter
            bind.rvPeculiarities.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            bind.btnNavigateToRoom.setOnClickListener {
                onClickToNavigate()
            }
        }
    }


    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return RoomHolder(
            ItemSuitBinding.inflate(
                LayoutInflater.from(parent.context), parent, false),
            glide,
            onClickToNavigate
        )
    }

    override fun bindViewHolder(model: RoomItem, viewHolder: RoomHolder) {
        viewHolder.onBind(model)
    }
}