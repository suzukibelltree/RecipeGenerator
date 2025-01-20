package com.example.recipegenerator.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavigation(navController: NavController) {
    /*
    一番下のナビゲーションバー
     */
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    TabRow(selectedTabIndex = selectedTabIndex) {
        NavigateTab(
            navController = navController,
            route = "ranking",
            txt = "ホーム",
            icon = Icons.Default.Home,
            onClick = { selectedTabIndex = 0 }
        )
        NavigateTab(
            navController = navController,
            route = "favorites",
            txt = "おきにいり",
            icon = Icons.Default.Favorite,
            onClick = { selectedTabIndex = 1 }
        )
        NavigateTab(
            navController = navController,
            route = "history",
            txt = "りれき",
            icon = Icons.Default.Refresh,
            onClick = { selectedTabIndex = 2 }
        )
    }
}

@Composable
fun NavigateTab(navController: NavController, route: String, txt: String, icon: ImageVector, onClick: () -> Unit) {
    Tab(
        selected =  false,
        onClick = { if (navController.currentDestination!!.route != route) {
            navController.navigate(route)
            onClick()
        } },
        text = { Text(text = txt) },
        icon = {
            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = icon,
                contentDescription = "アイコン"
            )
        },
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
    )
}