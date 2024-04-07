package com.example.chatapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.chatapp.Constants
import com.example.chatapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun error(textInputLayout: TextInputLayout, errorMsg: String?) {
    textInputLayout.error = errorMsg
}

@BindingAdapter("image")
fun imageBasedOnCategory(image: ImageView, category: String) {
    if (category.equals(Constants.MOVIES)) {
        image.setImageResource(R.drawable.image_movies_cat)
    } else if (category.equals(Constants.SPORTS)) {
        image.setImageResource(R.drawable.image_sports_cat)
    } else {
        image.setImageResource(R.drawable.image_music_cat)
    }
}