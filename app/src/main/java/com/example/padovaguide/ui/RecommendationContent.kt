package com.example.padovaguide.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.padovaguide.R
import com.example.padovaguide.data.Recommendation
import com.example.padovaguide.data.local.LocalRecommendationsDataProvider
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ListOnlyContent(
    padovaguideUiState: PadovaguideUiState,
    onRecommendationClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    val recommendations = padovaguideUiState.currentCategoryRecommendations

    LazyColumn (
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(recommendations, key={recommendation -> recommendation.id}) { recommendation ->
            RecommendationItem(
                recommendation = recommendation,
                painter = painterResource(R.drawable.coffee_icon),
                selected = false,
                onCardClick = {
                    onRecommendationClick(recommendation)
                }
            )
        }
    }
}

@Composable
fun RecommendationItem(
    recommendation: Recommendation,
    painter: Painter,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = onCardClick
    ) {
        Column (
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp
                    )
            ) {
                Icon(
                    painter = painter,
                    contentDescription = "Coffee icon"
                )
                Text(
                    text = stringResource(recommendation.name),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            start = 10.dp
                        )
                )
            }
            Text(
                text = stringResource(recommendation.description),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun PreviewRecommendationItem(){
    RecommendationItem(
        recommendation = LocalRecommendationsDataProvider.defaultRecommendation,
        painter = painterResource(R.drawable.coffee_icon),
        selected = false,
        onCardClick = {},
    )
}

@Composable
fun AppContent() {}