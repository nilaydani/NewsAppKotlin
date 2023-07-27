package com.example.nilay.presentation.news_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nilay.presentation.discover.Discover
import com.example.nilay.presentation.favorites.Favorites
import com.example.nilay.presentation.search.Search

@Composable
fun Navigation(modifier: Modifier, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.BottomBarScreens.Discover.route
    ) {

        composable(Screens.BottomBarScreens.Discover.route) {
            Discover(navController = navController)
        }

        composable(Screens.BottomBarScreens.Search.route){
            Search(navController = navController)
        }

        composable(Screens.BottomBarScreens.Favorites.route){
            Favorites(navController = navController)
        }

//        composable(
//            Screens.NewsDetailsScreen.withArgs("Id"),
//            arguments = Screens.NewsDetailsScreen.arguments!!
//        ){
//
//        }

    }


}