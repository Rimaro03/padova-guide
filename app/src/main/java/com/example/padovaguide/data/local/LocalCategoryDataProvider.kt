package com.example.padovaguide.data.local

import com.example.padovaguide.R
import com.example.padovaguide.data.Category

object LocalCategoryDataProvider {
    val allCategories = listOf(
        Category(
            id = 0L,
            name = R.string.coffee,
            icon = R.drawable.coffee_icon
        ),
        Category(
            id = 1L,
            name = R.string.restaurants,
            icon = R.drawable.restaurant_icon
        ),
        Category(
            id = 2L,
            name = R.string.kidplaces,
            icon = R.drawable.kids_places_icon
        ),
        Category(
            id = 3L,
            name = R.string.parks,
            icon = R.drawable.parks_icon
        )
    )

    /**
     * Get the category with the given [categoryID].
     */
    fun getCategoryByID(categoryID: Long): Category {
        return allCategories.firstOrNull() { it.id == categoryID }
            ?: allCategories.first()
    }

    val defaultCategory = allCategories.first()
}