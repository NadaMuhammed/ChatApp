package com.example.chatapp.data.repositories.rooms_repo

import com.example.chatapp.Constants
import com.example.chatapp.data.model.Room
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RoomsRepoImpl: RoomsRepo {
    override suspend fun createRoom(title: String, category: String, description: String) {
        val document = Firebase.firestore.collection(Constants.ROOMS).document()
        val room = Room(document.id, title, category, description)
        document.set(room).await()
    }
}