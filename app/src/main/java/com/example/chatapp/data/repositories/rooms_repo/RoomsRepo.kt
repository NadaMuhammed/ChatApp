package com.example.chatapp.data.repositories.rooms_repo

import com.example.chatapp.data.model.Room

interface RoomsRepo {
    suspend fun createRoom(title: String, category: String, description: String)
    suspend fun getRooms(): List<Room>
}