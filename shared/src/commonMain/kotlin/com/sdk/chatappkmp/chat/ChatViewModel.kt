package com.sdk.chatappkmp.chat

import com.sdk.chatappkmp.model.Message
import com.sdk.chatappkmp.model.Request
import com.sdk.chatappkmp.timeToString
import com.sdk.chatappkmp.timestampMs
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()
    private val messageList = mutableListOf<Message>()
    private var time = ""

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    init {
        viewModelScope.launch {
            val init = launchBot("Hel")
            time = init.time
        }
    }

    fun sendMessage() {
        val random = (1..4).random()
        viewModelScope.launch {
            messageList.add(
                Message(
                    userId = 1,
                    msg = _state.value.userText,
                    time = time // timeToString(timestampMs()) -> it has worked incorrectly
                )
            )
            val botMessage = launchBot(_state.value.userText)
            time = botMessage.time
            _state.update {
                it.copy(
                    isTyping = true,
                    messageList = messageList,
                    userText = ""
                )
            }
            delay(random * 1000L)
            messageList.add(botMessage)
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

    private suspend fun launchBot(requestText: String): Message {
        val response = httpClient.post("http://84.54.74.228:8080/request") {
            contentType(ContentType.Application.Json)
            setBody(Request(requestText))
        }.body<Message>()
        return response
    }
}