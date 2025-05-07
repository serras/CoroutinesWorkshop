package com.kotlinconf.workshop.chat

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.kotlinconf.workshop.chat.network.NetworkChatService
import com.kotlinconf.workshop.chat.ui.ChatView
import com.kotlinconf.workshop.chat.ui.ChatViewModel
import io.chozzle.composemacostheme.MacTheme

@Composable
@Preview
fun ChatAppSecond(chatViewModel: ChatViewModel) {
    MacTheme {
        Box(Modifier.fillMaxSize().padding(10.dp)) {
            ChatView(chatViewModel)
        }
    }
}

fun main() = application {
    val chatService = remember { NetworkChatService() }
    val chatViewModel = remember { ChatViewModel(chatService) }
    LaunchedEffect(true) {
        chatService.ensureServerIsRunning()
    }
    Window(
        onCloseRequest = {
            exitApplication()
        },
        title = "Chat example",
        state = rememberWindowState(width = 500.dp, height = 300.dp),
    ) {
        ChatAppSecond(chatViewModel)
    }
}