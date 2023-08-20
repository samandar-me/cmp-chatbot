package com.sdk.chatappkmp.chat

import com.sdk.chatappkmp.model.Message

data class ChatState(
    val isTyping: Boolean = false,
    val userText: String = "",
    val messageList: List<Message> = emptyList()
)