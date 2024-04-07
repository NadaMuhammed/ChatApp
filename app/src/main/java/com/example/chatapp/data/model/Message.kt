package com.example.chatapp.data.model

import com.google.firebase.Timestamp

data class Message(
    val id: String = "",
    val message: String = "",
    val timestamp: Timestamp = Timestamp.now(),
    val senderId: String = "",
    val senderName: String = ""
)