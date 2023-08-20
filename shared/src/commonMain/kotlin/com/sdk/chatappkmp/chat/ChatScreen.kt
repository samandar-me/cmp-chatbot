package com.sdk.chatappkmp.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.sdk.chatappkmp.component.MessageItem
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

data class ChatScreen(
    private val viewModel: ChatViewModel,
) : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val state by viewModel.state.collectAsState()
        val listState = rememberLazyListState()
        if (state.messageList.isNotEmpty()) {
            LaunchedEffect(state.messageList.last()) {
                listState.animateScrollToItem(state.messageList.lastIndex, scrollOffset = 2)
            }
        }
        Scaffold(
            topBar = {
                ListItem(
                    leadingContent = {
                        Box(
                            Modifier
                                .size(45.dp)
                                .clip(CircleShape)
                                .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource("img_1.png"),
                                contentDescription = "image"
                            )
                        }
                    },
                    headlineContent = {
                        Text(
                            text = "SamGPT AI",
                            fontSize = 22.sp
                        )
                    },
                    supportingContent = {
                        Text(
                            text = if (state.isTyping) "typing..." else "online",
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 17.sp
                        )
                    }
                )
            }
        ) { p ->
            Column(
                modifier = Modifier
                    .padding(p)
            ) {
                BoxWithConstraints {
                    LazyColumn(
                        state = listState,
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.height(maxHeight - 75.dp)
                    ) {
                        items(
                            items = state.messageList
                        ) { message ->
                            MessageItem(message)
                        }
                    }
                }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    value = state.userText,
                    onValueChange = viewModel::onTextValueChange,
                    placeholder = {
                        Text(text = "Your message...")
                    },
                    trailingIcon = {
                        if (state.userText.isNotBlank()) {
                            IconButton(
                                onClick = {
                                    viewModel.sendMessage()
                                }
                            ) {
                                Icon(Icons.Default.Send, "send")
                            }
                        }
                    }
                )
            }
        }
    }
}