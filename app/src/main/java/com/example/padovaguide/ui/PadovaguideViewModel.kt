package com.example.padovaguide.ui

import androidx.lifecycle.ViewModel
import com.example.padovaguide.data.Category
import com.example.padovaguide.data.CategoryType
import com.example.padovaguide.data.Recommendation
import com.example.padovaguide.data.local.LocalRecommendationsDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PadovaguideViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(PadovaguideUiState())
    val uiState: StateFlow<PadovaguideUiState> = _uiState

    init {
        initUIState()
    }

    private fun initUIState(){
        val categories: Map<CategoryType, List<Recommendation>> =
            LocalRecommendationsDataProvider.allRecommendations.groupBy { it.category }
        _uiState.value = PadovaguideUiState(
            categories = categories,
            currentRecommendation = categories[CategoryType.Coffee]?.get(0)
                ?: LocalRecommendationsDataProvider.defaultRecommendation
        )
    }

    fun updateCurrentRecommendation(recommendation: Recommendation) {
        _uiState.update {
            it.copy(
                currentRecommendation = recommendation
            )
        }
    }
}