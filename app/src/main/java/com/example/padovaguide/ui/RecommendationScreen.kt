package com.example.padovaguide.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.padovaguide.data.CategoryType
import com.example.padovaguide.ui.utils.ContentType
import com.example.padovaguide.ui.utils.NavigationType
import com.example.padovaguide.R
import com.example.padovaguide.data.Recommendation

@Composable
fun RecommendationScreen(
    padovaguideUiState: PadovaguideUiState,
    navigationType: NavigationType,
    contentType: ContentType,
    onRecommendationClick: (Recommendation) -> Unit
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            category = CategoryType.Coffee,
            icon = painterResource(R.drawable.coffee_icon),
            text = stringResource(id = R.string.coffee)
        ),
        NavigationItemContent(
            category = CategoryType.Restaurants,
            icon = painterResource(R.drawable.restaurant_icon),
            text = stringResource(id = R.string.restaurants)
        ),
        NavigationItemContent(
            category = CategoryType.Kidplaces,
            icon = painterResource(R.drawable.kids_places_icon),
            text = stringResource(id = R.string.kidplaces)
        ),
        NavigationItemContent(
            category = CategoryType.Parks,
            icon = painterResource(R.drawable.parks_icon),
            text = stringResource(id = R.string.parks)
        ),
    )

    Box() {
        ListOnlyContent(
            padovaguideUiState = padovaguideUiState,
            onRecommendationClick = onRecommendationClick,
            modifier = Modifier,
        )
    }
}

private data class NavigationItemContent(
    val category: CategoryType,
    val icon: Painter,
    val text: String
)
