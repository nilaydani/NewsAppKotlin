package com.example.nilay.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.nilay.common.Resource
import com.example.nilay.domain.model.Article
import com.example.nilay.presentation.components.CustomTopBar
import com.example.nilay.presentation.components.MainBottomBar
import com.example.nilay.presentation.components.SearchBar
import com.example.nilay.presentation.components.vertical_news_list.VerticalNewsListItem
import com.example.nilay.presentation.search.components.SearchBodyLottie

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Search(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.news.collectAsState()
    val lazyListState = rememberLazyListState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val showLottie = remember {
        mutableStateOf(true)
    }
    Scaffold(
        topBar = {
            Column(modifier = Modifier) {
                CustomTopBar(subtitle = "Search")
                SearchBar(
                    onSearch = {
                        showLottie.value = false
                        keyboardController?.hide()
                        //or hide keyboard
                        focusManager.clearFocus()
                        viewModel.getNews(it)
                    }
                )
            }
        },
        bottomBar = { MainBottomBar(navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

            if (showLottie.value) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(paddingValues)
                ) {

                    SearchBodyLottie()
                }
            }
            when (uiState) {
                is Resource.Loading -> {
                    // Show a loading indicator
                    if (!showLottie.value) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                        ) {
                            CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
                        }
                    }
                }

                is Resource.Success -> {
                    showLottie.value = false
                    // Extract the list of articles from the UI state
                    val articles = (uiState as Resource.Success<List<Article>>).data
                    // Show the list of articles using a LazyColumn
                    LazyColumn(
                        modifier = modifier
                            .fillMaxWidth(), state = lazyListState
                    ) {
                        items(articles) { article ->
                            VerticalNewsListItem(modifier, navController, article)
                        }

                    }
                }

                is Resource.Error -> {
                    showLottie.value = false
                    // Show an error message
                    Text(
                        text = (uiState as Resource.Error).errorMessage,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }


    }


}

