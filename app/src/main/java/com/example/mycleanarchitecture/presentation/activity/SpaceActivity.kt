package com.example.mycleanarchitecture.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycleanarchitecture.presentation.screens.detail.SpaceDetailsScreen
import com.example.mycleanarchitecture.presentation.screens.list.SpaceListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpaceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.List.route
            ) {
                composable(Screen.List.route) {
                    SpaceListScreen(onItemClick = { launchId ->
                        navController.navigate("details/$launchId")
                    })
                }
                composable(Screen.Details.route, arguments = listOf(navArgument("id") {
                    type = NavType.LongType
                })) {
                    SpaceDetailsScreen(
                        navController = navController,
                        launchId = it.arguments?.getLong("id") ?: -1L
                    )
                }
            }
        }
    }

}

sealed class Screen(val route: String) {
    object List : Screen("list")
    object Details : Screen("details/{id}")
}