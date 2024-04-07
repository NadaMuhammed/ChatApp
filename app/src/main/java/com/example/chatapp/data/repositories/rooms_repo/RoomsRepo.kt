package com.example.chatapp.data.repositories.rooms_repo

import com.example.chatapp.data.model.Message
import com.example.chatapp.data.model.Room
import kotlinx.coroutines.flow.Flow

interface RoomsRepo {
    suspend fun createRoom(title: String, category: String, description: String)
    suspend fun getRooms(): List<Room>
    suspend fun sendMessage(message: String, roomId: String)
    suspend fun getMessages(roomId: String): Flow<List<Message>>
}