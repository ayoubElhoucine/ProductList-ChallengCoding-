package com.example.challengcodingapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.challengcodingapp.model.Item
import com.example.challengcodingapp.ui.home.HomeScreen
import com.example.challengcodingapp.ui.details.DetailsScreen

object Screens {
    const val HOME = "home_screen"
    const val DETAILS = "details_screen"
}

@Composable
fun AppNavGraph(
    finishActivity: () -> Unit = {},
    appState: AppState,
    startDestination: String = Screens.HOME,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(Screens.HOME) {
            BackHandler(onBack = appState::popBack)
            HomeScreen {
                appState.navController.currentBackStackEntry?.savedStateHandle?.set(key = "item", value = it)
                appState.navigateTo(Screens.DETAILS)
            }
        }

        composable(Screens.DETAILS) {
            BackHandler(onBack = appState::popBack)
            appState.navController.previousBackStackEntry?.savedStateHandle?.get<Item>("item")?.let { item ->
                DetailsScreen(
                    item = item,
                    onBack = appState::popBack
                )
            }
        }

    }
}