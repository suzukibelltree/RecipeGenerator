package com.example.recipegenerator.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeNavigation(navController: NavController) {
    /*
    ホーム画面に表示するNavigationBar
     */

    var selectedTabIndex by remember {
        mutableStateOf( 0 )
    }

    TabRow(selectedTabIndex = selectedTabIndex) {
        NavigateTextTab(
            navController = navController,
            route = "ranking",
            txt = "ランキング",
            onClick = { selectedTabIndex = 0 }
        )
        NavigateTextTab(
            navController = navController,
            route = "categories",
            txt = "カテゴリ",
            onClick = { selectedTabIndex = 1 }
        )
    }
}

@Composable
fun NavigateTextTab(navController: NavController, route: String, txt: String, onClick: () -> Unit) {
    Tab(
        selected =  false,
        onClick = { if (navController.currentDestination!!.route != route) {
            navController.navigate(route)
            onClick()
        } },
        text = { Text(text = txt) },
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
    )
}