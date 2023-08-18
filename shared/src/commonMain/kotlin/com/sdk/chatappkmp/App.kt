package com.sdk.chatappkmp

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.sdk.chatappkmp.chat.ChatScreen

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
){
    ChatAppTheme(
        dynamicTheme = dynamicColor,
        darkTheme = darkTheme
    ) {
        Navigator(ChatScreen)
    }
}