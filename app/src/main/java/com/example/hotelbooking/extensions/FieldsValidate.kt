package com.example.hotelbooking.extensions

import android.graphics.Color
import android.widget.EditText
import com.example.hotelbooking.R

fun EditText.setValidator(validator: (String) -> Boolean){

    setOnFocusChangeListener { v, hasFocus ->
        if(hasFocus){
            v.post {
                v.background.setTint(this.context.getColor(R.color.field_normal))
            }
        } else {
            val text = (v as EditText).text.toString()
            val isValid = validator(text)
            if(!isValid){
                v.post {
                    v.background.setTint(this.context.getColor(R.color.field_error))
                }
            }
        }
    }


}