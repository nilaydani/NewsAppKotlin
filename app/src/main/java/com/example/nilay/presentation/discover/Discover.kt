package com.example.nilay.presentation.discover

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nilay.common.Resource
import com.example.nilay.domain.model.Article
import com.example.nilay.presentation.components.CustomTopBar
import com.example.nilay.presentation.components.MainBottomBar
import com.example.nilay.presentation.components.vertical_news_list.VerticalNewsListItem
import com.example.nilay.presentation.discover.components.DiscoverSearchCard
import com.example.nilay.presentation.news_app.Screens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Discover(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NewsViewModel = hiltViewModel()

) {
    val uiState by viewModel.news.collectAsState()
    val lastVisibleItemIndex = remember{
        derivedStateOf { 0 }
    }
    Scaffold(
        topBar = { CustomTopBar(subtitle = "Discover") },
        bottomBar = { MainBottomBar(navController = navController) }
    ) { paddingValues ->

        when (uiState) {
            is Resource.Loading -> {
                // Show a loading indicator
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier.fillMaxHeight().fillMaxWidth()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
                }
            }

            is Resource.Success -> {
                // Extract the list of articles from the UI state
                val articles = (uiState as Resource.Success<List<Article>>).data
                // Show the list of articles using a LazyColumn
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            layout(placeable.width, placeable.height) {
                                placeable.place(0, 120) // Position the column below the image
                            }
                        }
                ) {
                    item {
                        DiscoverSearchCard(
                            modifier = Modifier.padding(top = 4.dp),
                            onClick = {
                                navController.navigate(Screens.BottomBarScreens.Search.route) {
                                    popUpTo(navController.graph.startDestinationId){
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }

                    items(articles) { article ->
                        VerticalNewsListItem(modifier,navController,article)
                    }
                    if (lastVisibleItemIndex.value == articles.lastIndex) {
                        viewModel.page.value +=1
                        viewModel.getNews()
                    }
                }
            }

            is Resource.Error -> {
                // Show an error message
                Text(text = (uiState as Resource.Error).errorMessage, modifier = Modifier.padding(16.dp))
            }
        }

    }
}
