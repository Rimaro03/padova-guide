package com.example.padovaguide.ui

import com.example.padovaguide.data.Category
import com.example.padovaguide.data.CategoryType
import com.example.padovaguide.data.Recommendation
import com.example.padovaguide.data.local.LocalRecommendationsDataProvider

data class PadovaguideUiState (
    val categories: Map<CategoryType, List<Recommendation>> = emptyMap(),
    val currentCategory: CategoryType = CategoryType.Coffee,
    val currentRecommendation: Recommendation = LocalRecommendationsDataProvider.defaultRecommendation
) {
    val currentCategoryRecommendations: List<Recommendation> by lazy { categories[currentCategory]!! }
}