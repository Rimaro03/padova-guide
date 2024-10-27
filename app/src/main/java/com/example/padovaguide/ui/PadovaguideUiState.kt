package com.example.padovaguide.ui

import com.example.padovaguide.data.Category
import com.example.padovaguide.data.Recommendation
import com.example.padovaguide.data.local.LocalCategoryDataProvider
import com.example.padovaguide.data.local.LocalRecommendationsDataProvider

data class PadovaguideUiState (
    val categories: Map<Category, List<Recommendation>> = emptyMap(),
    val currentCategory: Category = LocalCategoryDataProvider.defaultCategory,
    val currentRecommendation: Recommendation = LocalRecommendationsDataProvider.defaultRecommendation
) {
    val currentCategoryRecommendations: List<Recommendation> by lazy { categories[currentCategory]!! }
}