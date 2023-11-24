package com.example.hotelbooking.adapters.delegateAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class DelegateAdapter<M, in VH : RecyclerView.ViewHolder>(val modelClass: Class<out M>) {

    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun bindViewHolder(model: M, viewHolder: VH)

    open fun bindWithPayloads(payloads: MutableList<Any>, viewHolder: VH){}

}