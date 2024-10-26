package com.example.padovaguide.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    val id: Long,
    @StringRes val name: Int,
    @DrawableRes val icon: Int
)
