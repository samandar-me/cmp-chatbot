package com.sdk.chatappkmp.component

import androidx.compose.runtime.Composable
import com.sdk.chatappkmp.model.Message

@Composable
fun MessageItem(message: Message) {
    if (message.userId == 0) {
        BotMessage(message)
    } else {
        UserMessage(message)
    }
}