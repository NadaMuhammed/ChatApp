package com.example.chatapp.data.repositories.auth_repo

import com.example.chatapp.Constants
import com.example.chatapp.data.model.User
import com.example.chatapp.data.repositories.auth_repo.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthRepoImpl: AuthRepo {
    override suspend fun register(email: String, username: String, password: String): User {
        val auth = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await();
        val user = User(auth.user!!.uid, email, username)
        Firebase.firestore.collection(Constants.USERS).document(auth.user!!.uid).set(user)
        return user
    }

    override suspend fun login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
    }
}