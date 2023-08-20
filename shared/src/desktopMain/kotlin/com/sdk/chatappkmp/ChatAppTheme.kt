package com.sdk.chatappkmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sdk.chatappkmp.ui.DarkColorScheme
import com.sdk.chatappkmp.ui.LightColorScheme
import com.sdk.chatappkmp.ui.Typography

@Composable
actual fun ChatAppTheme(
    dynamicTheme: Boolean,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}