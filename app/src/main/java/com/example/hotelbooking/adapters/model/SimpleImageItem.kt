package com.example.hotelbooking.adapters.model

import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapterItem

data class SimpleImageItem(
    val url : String
) : DelegateAdapterItem {
    override fun id(): Any {
        return url
    }

    override fun content(): Any {
        return url
    }
}
