package com.example.chatapp.data.model

data class ViewMessage(
    var title: String?,
    var message: String?,
    var posButtonTitle: String?,
    var negButtonTitle: String?,
    var onPosClick: (() -> Unit)? = null,
    var onNegClick: (() -> Unit)? = null
)