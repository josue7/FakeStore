package com.technical.practice.fakestore.ui.tab

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.technical.practice.fakestore.R

data class TabItem(
    val title: Int,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector
)

val tabs = listOf(
    TabItem (
        title = R.string.tab_title_product,
        iconSelected = Icons.Filled.Home,
        iconUnselected = Icons.Outlined.Home,
    ),
    TabItem(
        title = R.string.tab_title_favorite,
        iconSelected = Icons.Filled.Favorite,
        iconUnselected = Icons.Outlined.Favorite
    )
)
