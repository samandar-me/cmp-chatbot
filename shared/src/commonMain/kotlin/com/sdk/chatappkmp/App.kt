package com.sdk.chatappkmp

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.sdk.chatappkmp.chat.ChatScreen
import com.sdk.chatappkmp.chat.ChatViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
){
    ChatAppTheme(
        dynamicTheme = dynamicColor,
        darkTheme = darkTheme
    ) {
        val viewModel = getViewModel(
            key = "chat-view-model",
            factory = viewModelFactory {
                ChatViewModel()
            }
        )
        Navigator(ChatScreen(viewModel))
    }
}