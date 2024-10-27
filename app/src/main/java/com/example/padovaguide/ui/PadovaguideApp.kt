package com.example.padovaguide.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.padovaguide.R
import com.example.padovaguide.data.Category
import com.example.padovaguide.data.Recommendation
import com.example.padovaguide.ui.utils.ContentType
import com.example.padovaguide.ui.utils.NavigationType

enum class AppScreen(@StringRes val title: Int) {
    Recommendation(title = R.string.recommendation),
    Details(title = R.string.detail),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    currentScreen: AppScreen,
    canNavigateBack: Boolean,
    navigateBack: () -> Unit = {},
    padovaguideUIState: PadovaguideUiState
) {
    TopAppBar(
        title = {
            if(currentScreen == AppScreen.Recommendation) {
                Text("PadovaGuide")
            }
            else {
                if(currentScreen == AppScreen.Details) {
                    Text(stringResource(padovaguideUIState.currentRecommendation.name))
                }
                else {
                    Text(stringResource(currentScreen.title))
                }
            }
        },
        modifier = modifier,
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer
//        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}


@Composable
fun PadovaguideApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass
) {
    val viewModel: PadovaguideViewModel = viewModel()
    val padovaguideUIState = viewModel.uiState.collectAsState().value

    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStack?.destination?.route ?: AppScreen.Recommendation.name
    )

    val context = LocalContext.current

    /*Defines:
    * - navigation type: bottom navigation, navigation rail or drawer
    * - content type: list only or list and detail
    */
    val navigationType: NavigationType
    val contentType: ContentType
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
            contentType = ContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = NavigationType.NAVIGATION_RAIL
            contentType = ContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = ContentType.LIST_ONLY
        }
        else -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
            contentType = ContentType.LIST_ONLY
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateBack = {navController.navigateUp()},
                padovaguideUIState = padovaguideUIState
            )
        },
        modifier = modifier
    ) {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.Recommendation.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(AppScreen.Recommendation.name) {
                RecommendationScreen(
                    padovaguideUiState = padovaguideUIState,
                    navigationType = navigationType,
                    contentType = contentType,
                    onRecommendationClick = {recommendation: Recommendation ->
                        viewModel.updateCurrentRecommendation(
                            recommendation = recommendation
                        )
                        navController.navigate(AppScreen.Details.name)
                    },
                    onTabPressed = {category: Category ->
                        viewModel.updateCurrentCategory(
                            category = category
                        )
                    }
                )
            }
            composable(AppScreen.Details.name) {
                DetailScreen(
                    padovaguideUiState = padovaguideUIState,
                    onOpenMapsClicked = { address: String ->
                        openMaps(context, address)
                    }
                )
            }
        }
    }
}

private fun openMaps(context: Context, address: String) {
    val gmmIntentUri = Uri.parse("geo:0,0?q=${address}, Padua")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")

    context.startActivity(mapIntent)
}