package com.sdk.chatappkmp.chat

import com.sdk.chatappkmp.model.Message
import com.sdk.chatappkmp.timeToString
import com.sdk.chatappkmp.timestampMs
import com.sdk.chatappkmp.util.BotResponse
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()
    private val messageList = mutableListOf<Message>()

    fun sendMessage() {
        val random = (1..4).random()
        viewModelScope.launch {
            messageList.add(
                Message(
                    userId = 1,
                    msg = _state.value.userText,
                    time = timeToString(timestampMs())
                )
            )
            val botResponse = BotResponse.botResponse(_state.value.userText.lowercase())
            _state.update {
                it.copy(
                    isTyping = true,
                    messageList = messageList,
                    userText = ""
                )
            }
            delay(random * 1000L)
            messageList.add(
                Message(
                    userId = 0,
                    msg = botResponse,
                    time = timeToString(timestampMs())
                )
            )
            _state.update {
                it.copy(
                    isTyping = false,
                    messageList = messageList
                )
            }
        }
    }

    fun onTextValueChange(text: String) {
        _state.update {
            it.copy(userText = text)
        }
    }
}