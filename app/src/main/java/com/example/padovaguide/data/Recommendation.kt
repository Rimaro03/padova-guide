package com.example.padovaguide.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recommendation(
    val id: Long,
    val category: Category,
    @StringRes val name: Int,
    @DrawableRes val image: Int,
    @StringRes val location: Int,
    @StringRes val description: Int
)
