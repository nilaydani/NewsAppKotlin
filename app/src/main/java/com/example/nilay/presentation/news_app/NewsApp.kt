package com.example.nilay.presentation.news_app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nilay.presentation.ui.theme.AppTheme


@Composable
fun NewsApp() {
    val navController: NavController = rememberNavController()

    AppTheme(useDarkTheme = isSystemInDarkTheme()) {
        Navigation(
            modifier = Modifier,
            navController = navController as NavHostController
        )
    }
}