package com.mis.route.chatapp

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun error(textInputLayout: TextInputLayout, errorMsg: String?){
    textInputLayout.error = errorMsg
}
