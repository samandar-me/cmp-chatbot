import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sdk.chatappkmp.App

fun main() {
    // I think we can't use ktor for desktop
    application {
        Window(
            title = "ChatBot",
            onCloseRequest = ::exitApplication
        ) {
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false
            )
        }
    }
}