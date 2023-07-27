package com.example.nilay.presentation.news_app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screens(
    val route: String,
    val arguments: List<NamedNavArgument>? = null
    ){

    object NewsDetailsScreen : Screens(
        route = "news/details",
        arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )
    )


    sealed class BottomBarScreens(
        route: String,
        val title: String,
        val icon: ImageVector? = null
    ) : Screens(route) {

        object Discover : BottomBarScreens(
            route = "discover",
            title = "Discover",
        )

        object Search : BottomBarScreens(
            route = "search",
            title = "Search",
            icon = Icons.Rounded.Search
        )

        object Favorites : BottomBarScreens(
            route = "favorites",
            title = "Favorites",
            icon = Icons.Rounded.Favorite
        )
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }

}
