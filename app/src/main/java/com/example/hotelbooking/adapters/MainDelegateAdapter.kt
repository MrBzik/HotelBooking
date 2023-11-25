package com.example.hotelbooking.adapters

import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapter
import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapterItem
import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapterItemDiffCallback
import com.example.hotelbooking.utils.Logger

class MainDelegateAdapter(
    private val delegates: SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>>
) : ListAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>(DelegateAdapterItemDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        for (i in 0 until delegates.size()) {
            if (delegates[i].modelClass == getItem(position).javaClass) {
                return delegates.keyAt(i)
            }
        }
        throw NullPointerException("Can not get viewType for position $position")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegates[viewType].createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val delegateAdapter = delegates[getItemViewType(position)]

        delegateAdapter.bindViewHolder(getItem(position), holder)
    }


    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if(payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val delegateAdapter = delegates[getItemViewType(position)]
            delegateAdapter.bindWithPayloads(payloads, holder)
        }
    }


    class Builder {

        private var count: Int = 0
        private val delegates: SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>> = SparseArray()

        fun add(delegateAdapter: DelegateAdapter<out DelegateAdapterItem, *>): Builder {

            delegates.put(count++, delegateAdapter as DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>)
            return this
        }

        fun build(): MainDelegateAdapter {
            require(count != 0) { "Register at least one adapter" }
            return MainDelegateAdapter(delegates)
        }
    }


}