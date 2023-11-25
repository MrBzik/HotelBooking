package com.example.hotelbooking.adapters.model

import android.text.Editable
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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


            bind.apply {
                etDateOfBirth.setText(tourist.touristInfo.dateOfBirth)
                etFirstName.setText(tourist.touristInfo.fistName)
                etLastName.setText(tourist.touristInfo.lastName)
                etPassport.setText(tourist.touristInfo.passportNumber)
                etPassportExpire.setText(tourist.touristInfo.passportExpire)
                etCitizenship.setText(tourist.touristInfo.citizenship)
            }
        }
    }





    override fun bindWithPayloads(payloads: MutableList<Any>, viewHolder: TouristHolder) {

        val field = payloads[0] as InputFields

        when(field){
            InputFields.FIRST_NAME -> viewHolder.bind.etFirstName.setError()
            InputFields.LAST_NAME -> viewHolder.bind.etLastName.setError()
            InputFields.DATE_OF_BIRTH -> viewHolder.bind.etDateOfBirth.setError()
            InputFields.PASSPORT -> viewHolder.bind.etPassport.setError()
            InputFields.PASSPORT_EXP -> viewHolder.bind.etPassportExpire.setError()
            InputFields.CITIZENSHIP -> viewHolder.bind.etCitizenship.setError()
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


        fun onTextChange(editText: EditText, field: InputFields){
            editText.addTextChangedListener { editable ->
                debounceJob = null
                debounceJob = CoroutineScope(Dispatchers.IO).launch {
                    delay(300)
                    cacheTextFieldsChanges(field, holder.adapterPosition, editable)
                }
            }
        }


        holder.bind.apply {

            onTextChange(etPassport, InputFields.PASSPORT)
            onTextChange(etPassportExpire, InputFields.PASSPORT_EXP)
            onTextChange(etFirstName, InputFields.FIRST_NAME)
            onTextChange(etLastName, InputFields.LAST_NAME)
            onTextChange(etDateOfBirth, InputFields.DATE_OF_BIRTH)
            onTextChange(etCitizenship, InputFields.CITIZENSHIP)

            etDateOfBirth.setSimpleValidator()
            etFirstName.setSimpleValidator()
            etLastName.setSimpleValidator()
            etPassport.setSimpleValidator()
            etPassportExpire.setSimpleValidator()
            etCitizenship.setSimpleValidator()

        }


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