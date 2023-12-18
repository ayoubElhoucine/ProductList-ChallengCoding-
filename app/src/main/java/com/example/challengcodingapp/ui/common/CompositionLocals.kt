package com.example.challengcodingapp.ui.common

import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.CoroutineScope


val LocalCoroutineScope = staticCompositionLocalOf<CoroutineScope> {
    error("No coroutine scope provided!")
}