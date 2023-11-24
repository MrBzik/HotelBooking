package com.example.hotelbooking.adapters.model

import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapterItem

data class SimpleTextItem(val text : String) : DelegateAdapterItem{
    override fun id(): Any {
        return text
    }

    override fun content(): Any {
        return text
    }
}
