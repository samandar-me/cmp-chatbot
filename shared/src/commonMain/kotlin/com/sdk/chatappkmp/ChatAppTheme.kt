package com.sdk.chatappkmp

import androidx.compose.runtime.Composable

@Composable
expect fun ChatAppTheme(
    dynamicTheme: Boolean,
    darkTheme: Boolean,
    content: @Composable () -> Unit
)