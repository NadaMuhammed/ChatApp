package com.example.chatapp.ui.auth.fragments.register

sealed class RegisterEvents{
    data object NavigateToHomeEvent: RegisterEvents()
    data object NavigateToLoginEvent: RegisterEvents()
    data object NavigateToResetPassword: RegisterEvents()
}