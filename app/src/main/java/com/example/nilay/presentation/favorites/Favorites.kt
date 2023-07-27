package com.example.nilay.presentation.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.nilay.R
import com.example.nilay.presentation.components.CustomTopBar
import com.example.nilay.presentation.components.MainBottomBar
import com.example.nilay.presentation.discover.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favorites(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: NewsViewModel = hiltViewModel()
) {

    val listState = rememberLazyListState()

    Scaffold(
        topBar = { CustomTopBar(subtitle = "Favorites") },
        bottomBar = { MainBottomBar(navController) }
    ) { paddingValues ->
        Box(modifier = modifier.padding(paddingValues).fillMaxWidth().fillMaxHeight()) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.favorites)
            )

            LottieAnimation(
                modifier = Modifier.size(200.dp).align(Alignment.Center),
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
        }
    }
}
