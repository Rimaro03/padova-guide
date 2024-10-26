package com.example.padovaguide.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.padovaguide.data.Category
import com.example.padovaguide.data.CategoryType
import com.example.padovaguide.data.Recommendation
import com.example.padovaguide.data.local.LocalRecommendationsDataProvider

@Composable
fun DetailScreen(
    padovaguideUiState: PadovaguideUiState,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false,
    onOpenMapsClicked: (address: String) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        DetailCard(
            recommendation = padovaguideUiState.currentRecommendation,
            onOpenMapsClicked = onOpenMapsClicked,
            modifier = if (isFullScreen) {
                Modifier.padding(horizontal = 24.dp)
            } else {
                Modifier
            }
        )
    }
}

@Composable
fun DetailCard(
    recommendation: Recommendation,
    modifier: Modifier = Modifier,
    onOpenMapsClicked: (address: String) -> Unit
){
    val address = "${stringResource(recommendation.name)}, ${stringResource(recommendation.location)}"
    Card (
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxHeight()
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 15.dp
                )
        ) {
            Column {
                Image(
                    painter = painterResource(recommendation.image),
                    contentDescription = stringResource(recommendation.name),
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.FillWidth
                )
                Column (
                    modifier = modifier
                        .padding(top = 30.dp)
                ) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = stringResource(recommendation.description),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = modifier
                            .padding(top = 10.dp)
                    )
                }
            }
            Button(
                onClick = {onOpenMapsClicked(address)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = "Open in maps",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailCardPreview(){
    DetailCard(
        recommendation = LocalRecommendationsDataProvider.defaultRecommendation,
        onOpenMapsClicked = {}
    )
}