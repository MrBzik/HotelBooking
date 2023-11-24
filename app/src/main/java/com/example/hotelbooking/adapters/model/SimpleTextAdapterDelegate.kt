package com.example.hotelbooking.adapters.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapter
import com.example.hotelbooking.databinding.ItemPeculiarityBinding

class SimpleTextAdapterDelegate : DelegateAdapter<SimpleTextItem, SimpleTextAdapterDelegate.TextHolder>(SimpleTextItem::class.java) {

    class TextHolder(val bind : ItemPeculiarityBinding) : RecyclerView.ViewHolder(bind.root){
        fun bind(item : SimpleTextItem){
            bind.tvItem.text = item.text
        }
    }


    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TextHolder(
            ItemPeculiarityBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun bindViewHolder(model: SimpleTextItem, viewHolder: TextHolder) {
            viewHolder.bind(model)
    }


}