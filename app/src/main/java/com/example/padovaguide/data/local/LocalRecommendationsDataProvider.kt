package com.example.padovaguide.data.local

import com.example.padovaguide.R
import com.example.padovaguide.data.CategoryType
import com.example.padovaguide.data.Recommendation

object LocalRecommendationsDataProvider {
    val allRecommendations = listOf(
        Recommendation(
            id = 4L,
            category = CategoryType.Coffee,
            name = R.string.coffee_1_name,
            image = R.drawable.coffee_1_image,
            location = R.string.coffee_1_location,
            description = R.string.coffee_1_description
        ),
        Recommendation(
            id = 5L,
            category = CategoryType.Coffee,
            name = R.string.coffee_2_name,
            image = R.drawable.coffee_2_image,
            location = R.string.coffee_2_location,
            description = R.string.coffee_2_description
        ),
        Recommendation(
            id = 6L,
            category = CategoryType.Coffee,
            name = R.string.coffee_3_name,
            image = R.drawable.coffee_3_image,
            location = R.string.coffee_3_location,
            description = R.string.coffee_3_description
        ),
        Recommendation(
            id = 7L,
            category = CategoryType.Restaurants,
            name = R.string.restaurant_1_name,
            image = R.drawable.restaurant_1_image,
            location = R.string.restaurant_1_location,
            description = R.string.restaurant_1_description
        ),
        Recommendation(
            id = 8L,
            category = CategoryType.Restaurants,
            name = R.string.restaurant_2_name,
            image = R.drawable.restaurant_2_image,
            location = R.string.restaurant_2_location,
            description = R.string.restaurant_2_description
        ),
        Recommendation(
            id = 9L,
            category = CategoryType.Restaurants,
            name = R.string.restaurant_3_name,
            image = R.drawable.restaurant_3_image,
            location = R.string.restaurant_3_location,
            description = R.string.restaurant_3_description
        ),
        Recommendation(
            id = 10L,
            category = CategoryType.Kidplaces,
            name = R.string.kidplace_1_name,
            image = R.drawable.kidplace_1_image,
            location = R.string.kidplace_1_location,
            description = R.string.kidplace_1_description
        ),
        Recommendation(
            id = 11L,
            category = CategoryType.Kidplaces,
            name = R.string.kidplace_2_name,
            image = R.drawable.kidplace_2_image,
            location = R.string.kidplace_2_location,
            description = R.string.kidplace_2_description
        ),
        Recommendation(
            id = 12L,
            category = CategoryType.Kidplaces,
            name = R.string.kidplace_3_name,
            image = R.drawable.kidplace_3_image,
            location = R.string.kidplace_3_location,
            description = R.string.kidplace_3_description
        ),
        Recommendation(
            id = 13L,
            category = CategoryType.Parks,
            name = R.string.park_1_name,
            image = R.drawable.park_1_image,
            location = R.string.park_1_location,
            description = R.string.park_1_description
        ),
        Recommendation(
            id = 14L,
            category = CategoryType.Parks,
            name = R.string.park_2_name,
            image = R.drawable.park_2_image,
            location = R.string.park_2_location,
            description = R.string.park_2_description
        ),
        Recommendation(
            id = 15L,
            category = CategoryType.Parks,
            name = R.string.park_3_name,
            image = R.drawable.park_3_image,
            location = R.string.park_3_location,
            description = R.string.park_3_description
        )
    )

    /**
     * Get the recommendation with the given [recommendationID].
     */
    fun getRecommendationByID(recommendationID: Long): Recommendation {
        return allRecommendations.firstOrNull() { it.id == recommendationID }
            ?: allRecommendations.first()
    }

    val defaultRecommendation = allRecommendations.first()
}