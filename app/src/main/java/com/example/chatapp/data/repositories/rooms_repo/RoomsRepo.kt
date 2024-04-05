package com.example.chatapp.data.repositories.rooms_repo

interface RoomsRepo {
    suspend fun createRoom(title: String, category: String, description: String)
}