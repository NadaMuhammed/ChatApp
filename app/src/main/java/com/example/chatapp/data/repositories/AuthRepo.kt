package com.example.chatapp.data.repositories

import com.example.chatapp.data.model.User

interface AuthRepo {
    fun register(email: String, username: String, password: String): User
    fun login(email: String, password: String)
}