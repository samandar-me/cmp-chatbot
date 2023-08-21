package com.sdk.chatappkmp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdk.chatappkmp.model.Message

@Composable
fun BotMessage(message: Message) {
    Box(Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .widthIn(min = 70.dp, max = 300.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .align(Alignment.CenterStart)
                .padding(8.dp)
        ) {
            Text(
                text = message.msg,
                fontSize = 18.sp
            )
            Spacer(Modifier.height(3.dp))
            Text(
                text = message.time,
                fontSize = 13.sp,
                modifier = Modifier.fillMaxWidth().align(Alignment.End),
                textAlign = TextAlign.End
            )
        }
    }
}