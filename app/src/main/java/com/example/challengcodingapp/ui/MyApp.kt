package com.example.challengcodingapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import com.example.challengcodingapp.Greeting
import com.example.challengcodingapp.ui.common.LocalCoroutineScope
import com.example.challengcodingapp.ui.theme.ChallengCodingAppTheme

@Composable
fun MyApp(
    finishActivity: () -> Unit,
) {
    val appState = rememberAppState()

    ChallengCodingAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CompositionLocalProvider(
                LocalCoroutineScope provides appState.coroutineScope,
            ) {
                AppNavGraph(
                    appState = appState,
                )
            }
        }
    }
}