package com.example.hotelbooking.adapters.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbooking.R
import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapter
import com.example.hotelbooking.databinding.ItemTouristBinding

class TouristsAdapterDelegate(private val onShrinkExpandArrowClick: (Int) -> Unit) : DelegateAdapter<TouristItem, TouristsAdapterDelegate.TouristHolder>(TouristItem::class.java) {

    class TouristHolder(val bind: ItemTouristBinding) : RecyclerView.ViewHolder(bind.root){
        fun onBind(tourist: TouristItem){
            if(tourist.touristInfo.isExpanded){
                bind.llInputContainer.isVisible = true
            } else bind.llInputContainer.visibility = View.GONE



            bind.tvMainTitle.text = bind.root.context.getString(R.string.tourist_item_label, tourist.touristInfo.touristLabel)

            if(tourist.touristInfo.isExpanded) bind.ivShrinkExpandArrow.setImageResource(R.drawable.vector_arrow_shrink)
            else bind.ivShrinkExpandArrow.setImageResource(R.drawable.vector_arrow_expand)


        }
    }

//    private var onShrinkExpandArrowClick : (() -> Unit)? = null
//    fun onShrinkExpandArrowClick(click : () -> Unit){
//        onShrinkExpandArrowClick = click
//    }
//

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TouristHolder(
            ItemTouristBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun bindViewHolder(model: TouristItem, viewHolder: TouristHolder) {
        viewHolder.onBind(tourist = model)

        viewHolder.bind.ivShrinkExpandArrow.setOnClickListener {

            model.touristInfo.isExpanded = !model.touristInfo.isExpanded

            onShrinkExpandArrowClick(viewHolder.adapterPosition)

        }
    }
}