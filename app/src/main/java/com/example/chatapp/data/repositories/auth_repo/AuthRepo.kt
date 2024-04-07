package com.example.chatapp.data.repositories.auth_repo

import com.example.chatapp.data.model.User

interface AuthRepo {
    suspend fun register(email: String, username: String, password: String): User
    suspend fun login(email: String, password: String): User
}