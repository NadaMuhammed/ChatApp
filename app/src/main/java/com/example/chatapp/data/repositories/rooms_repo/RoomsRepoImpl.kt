package com.example.chatapp.data.repositories.rooms_repo

import android.util.Log
import com.example.chatapp.Constants
import com.example.chatapp.data.model.Message
import com.example.chatapp.data.model.Room
import com.example.chatapp.data.model.UserProvider
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RoomsRepoImpl : RoomsRepo {
    override suspend fun createRoom(title: String, category: String, description: String) {
        val document = Firebase.firestore.collection(Constants.ROOMS).document()
        val room = Room(document.id, title, category, description)
        document.set(room).await()
    }

    override suspend fun getRooms(): List<Room> {
        val collection = Firebase.firestore.collection(Constants.ROOMS)
        val querySnapshot = collection.get().await()
        return querySnapshot.toObjects(Room::class.java)
    }

    override suspend fun sendMessage(message: String, roomId: String) {
        val roomDocument =
            FirebaseFirestore.getInstance().collection(Constants.ROOMS).document(roomId)
        val messageDocument = roomDocument.collection(Constants.MESSAGES).document()
        val roomMessage = Message(
            messageDocument.id,
            message,
            Timestamp.now(),
            UserProvider.user!!.id!!,
            UserProvider.user!!.username!!
        )
        messageDocument.set(roomMessage).await()
    }

    override suspend fun getMessages(roomId: String): Flow<List<Message>> = flow {
        FirebaseFirestore.getInstance().collection(Constants.ROOMS).document(roomId)
            .collection(Constants.MESSAGES).snapshots().collect {
                emit(it.toObjects(Message::class.java))
            }
    }
}