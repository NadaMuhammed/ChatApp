package com.example.chatapp.data.model

import java.io.Serializable

data class Room(
    val id: String = "",
    val title: String = "",
    val category: String = "",
    val description: String = ""
): Serializable