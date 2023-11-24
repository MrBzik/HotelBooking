package com.example.hotelbooking.adapters.model

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbooking.R
import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapter
import com.example.hotelbooking.databinding.ItemTouristBinding
import com.example.hotelbooking.extensions.setError
import com.example.hotelbooking.extensions.setSimpleValidator
import com.example.hotelbooking.utils.FieldsMasks
import com.example.hotelbooking.utils.InputFields
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TouristsAdapterDelegate(
    private val onShrinkExpandArrowClick: (Int) -> Unit,
    private val cacheTextFieldsChanges : (field: InputFields, pos: Int, value: Editable?) -> Unit
) : DelegateAdapter<TouristItem, TouristsAdapterDelegate.TouristHolder>(TouristItem::class.java) {

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




    override fun bindWithPayloads(payloads: MutableList<Any>, viewHolder: TouristHolder) {

        val field = payloads[0] as InputFields

        when(field){
            InputFields.FIRST_NAME -> viewHolder.bind.etFirstName.setError()
            InputFields.LAST_NAME -> viewHolder.bind.etLastName.setError()
            InputFields.DATE_OF_BIRTH -> viewHolder.bind.etPassport.setError()
            InputFields.PASSPORT -> viewHolder.bind.etPassportExpire.setError()
            InputFields.PASSPORT_EXP -> viewHolder.bind.etCitizenship.setError()
            InputFields.CITIZENSHIP -> viewHolder.bind.etDateOfBirth.setError()
            else ->{}
        }

    }



    private var debounceJob : Job? = null

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val holder = TouristHolder(
            ItemTouristBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

        MaskedTextChangedListener.Companion.installOn(
            holder.bind.etDateOfBirth,
            FieldsMasks.Mask.DATE.mask
        )


        MaskedTextChangedListener.Companion.installOn(
            holder.bind.etPassportExpire,
            FieldsMasks.Mask.DATE.mask
        )


        holder.bind.etPassport.addTextChangedListener { editable ->
            debounceJob = null
            debounceJob = CoroutineScope(Dispatchers.IO).launch {
                delay(300)
                cacheTextFieldsChanges(InputFields.PASSPORT, holder.adapterPosition, editable)
            }
        }

        holder.bind.etPassportExpire.addTextChangedListener { editable ->
            debounceJob = null
            debounceJob = CoroutineScope(Dispatchers.IO).launch {
                delay(300)
                cacheTextFieldsChanges(InputFields.PASSPORT_EXP, holder.adapterPosition, editable)
            }
        }

        holder.bind.etFirstName.addTextChangedListener { editable ->
            debounceJob = null
            debounceJob = CoroutineScope(Dispatchers.IO).launch {
                delay(300)
                cacheTextFieldsChanges(InputFields.FIRST_NAME, holder.adapterPosition, editable)
            }
        }

        holder.bind.etLastName.addTextChangedListener { editable ->
            debounceJob = null
            debounceJob = CoroutineScope(Dispatchers.IO).launch {
                delay(300)
                cacheTextFieldsChanges(InputFields.LAST_NAME, holder.adapterPosition, editable)
            }
        }

        holder.bind.etDateOfBirth.addTextChangedListener { editable ->
            debounceJob = null
            debounceJob = CoroutineScope(Dispatchers.IO).launch {
                delay(300)
                cacheTextFieldsChanges(InputFields.DATE_OF_BIRTH, holder.adapterPosition, editable)
            }
        }

        holder.bind.etCitizenship.addTextChangedListener { editable ->
            debounceJob = null
            debounceJob = CoroutineScope(Dispatchers.IO).launch {
                delay(300)
                cacheTextFieldsChanges(InputFields.CITIZENSHIP, holder.adapterPosition, editable)
            }
        }


        holder.bind.etDateOfBirth.setSimpleValidator()
        holder.bind.etFirstName.setSimpleValidator()
        holder.bind.etLastName.setSimpleValidator()
        holder.bind.etPassport.setSimpleValidator()
        holder.bind.etPassportExpire.setSimpleValidator()
        holder.bind.etCitizenship.setSimpleValidator()


        return holder

    }

    override fun bindViewHolder(model: TouristItem, viewHolder: TouristHolder) {
        viewHolder.onBind(tourist = model)

        viewHolder.bind.ivShrinkExpandArrow.setOnClickListener {

            model.touristInfo.isExpanded = !model.touristInfo.isExpanded

            onShrinkExpandArrowClick(viewHolder.adapterPosition)
        }
    }
}