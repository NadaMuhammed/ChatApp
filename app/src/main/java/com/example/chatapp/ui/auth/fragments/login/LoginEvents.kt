package com.example.chatapp.ui.auth.fragments.login

sealed class LoginEvents {
    data object NavigateToRegister: LoginEvents()
    data object NavigateToHome: LoginEvents()
}