package com.technical.practice.fakestore.ui.product

import com.technical.practice.fakestore.R
import com.technical.practice.fakestore.ui.navigation.NavigationDestination

object ProductFavoriteDestination: NavigationDestination {
    override val route = "product_favorite"
    override val titleRes = R.string.tab_title_favorite
}