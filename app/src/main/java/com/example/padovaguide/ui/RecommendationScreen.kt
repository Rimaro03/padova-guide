package com.example.padovaguide.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.padovaguide.ui.utils.ContentType
import com.example.padovaguide.ui.utils.NavigationType
import com.example.padovaguide.R
import com.example.padovaguide.data.Category
import com.example.padovaguide.data.Recommendation
import com.example.padovaguide.data.local.LocalCategoryDataProvider

@Composable
fun RecommendationScreen(
    padovaguideUiState: PadovaguideUiState,
    navigationType: NavigationType,
    contentType: ContentType,
    onRecommendationClick: (Recommendation) -> Unit,
    onTabPressed: (Category) -> Unit
) {
    // Generate item list for drawer, bottom bar, nav rail with mock data
    var navigationItemContentList = emptyList<NavigationItemContent>()
    for (i in (0L .. 3L)) {
        val category = LocalCategoryDataProvider.getCategoryByID(i)
        val item = NavigationItemContent(
            category = category,
            icon = painterResource(category.icon),
            text = stringResource(category.name)
        )
        navigationItemContentList += item
    }

    /* If the navigation type is a perm drawer, it is created here with the app content parsed in its body.
    Otherwise only the app content is rendered, which shows either a navigation rail or the bottom navigation,
    depending on the "navigationType" variable
    * */
    if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet (
                    modifier = Modifier.width(240.dp),
                    drawerContentColor = MaterialTheme.colorScheme.inverseOnSurface
                ) {
                    NavigationDrawerContent(
                        selectedDestination = padovaguideUiState.currentCategory,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .padding(12.dp)
                    )
                }
            }
        ) {
            AppContent(
                navigationType = navigationType,
                contentType = contentType,
                padovaguideUiState = padovaguideUiState,
                onTabPressed = onTabPressed,
                onRecommendationPressed = onRecommendationClick,
                navigationItemContentList = navigationItemContentList
            )
        }
    }
    else {
        AppContent(
            navigationType = navigationType,
            contentType = contentType,
            padovaguideUiState = padovaguideUiState,
            onTabPressed = onTabPressed,
            onRecommendationPressed = onRecommendationClick,
            navigationItemContentList = navigationItemContentList
        )
    }
}

@Composable
private fun AppContent(
    modifier: Modifier = Modifier,
    navigationType: NavigationType,
    contentType: ContentType,
    padovaguideUiState: PadovaguideUiState,
    onTabPressed: ((Category) -> Unit),
    onRecommendationPressed: (Recommendation) -> Unit,
    navigationItemContentList: List<NavigationItemContent>
){
    Box(modifier = modifier) {
        Row (modifier = modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
                AppNavigationRail(
                    currentTab = padovaguideUiState.currentCategory,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList,
                )
            }
        }
        Column (
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface),
        ) {

            ListOnlyContent(
                padovaguideUiState = padovaguideUiState,
                onRecommendationClick = onRecommendationPressed,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            )
            AnimatedVisibility(visible = navigationType == NavigationType.BOTTOM_NAVIGATION) {
                AppBottomNavigation(
                    currentTab = padovaguideUiState.currentCategory,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun AppNavigationRail(
    modifier: Modifier = Modifier,
    currentTab: Category,
    onTabPressed: ((Category) -> Unit),
    navigationItemContentList: List<NavigationItemContent>
) {
    NavigationRail(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationRailItem(
                selected = currentTab == navItem.category,
                onClick = {onTabPressed(navItem.category)},
                icon = {
                    Icon(
                        painter = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

@Composable
private fun AppBottomNavigation(
    modifier: Modifier = Modifier,
    currentTab: Category,
    onTabPressed: ((Category) -> Unit),
    navigationItemContentList: List<NavigationItemContent>
){
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.category,
                onClick = {onTabPressed(navItem.category)},
                icon = {
                    Icon(
                        painter = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

@Composable
private fun NavigationDrawerContent(
    modifier: Modifier = Modifier,
    selectedDestination: Category,
    onTabPressed: ((Category) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
){
    Column {
        //Header
        for (navItem in navigationItemContentList) {
            NavigationDrawerItem(
                selected = navItem.category == selectedDestination,
                label = {
                    Text(
                        text = navItem.text,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                },
                icon = {
                    Icon(
                        painter = navItem.icon,
                        contentDescription = navItem.text
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ),
                onClick = { onTabPressed(navItem.category) }
            )
        }
    }
}

private data class NavigationItemContent(
    val category: Category,
    val icon: Painter,
    val text: String
)
