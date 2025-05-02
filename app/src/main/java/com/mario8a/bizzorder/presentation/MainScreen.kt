package com.mario8a.bizzorder.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mario8a.bizzorder.presentation.navigation.BottomNavigationBar
import com.mario8a.bizzorder.presentation.navigation.Screen

@Composable
fun MainScreen(
    navHostController: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navHostController)
        }
    ) { innerPadding ->

        NavHost(
            navController = navHostController,
            startDestination = Screen.Home.route,
        ) {
            composable(Screen.Home.route) {
                HomeScreen(modifier = Modifier.padding(innerPadding)) {
                    navHostController.navigate(Screen.DetailOrder.detDetailRoute(it))
                }
            }
            composable(Screen.Create.route) {
                CreateScreen()
            }
            composable(Screen.PreOrders.route) {
                PreOrdersScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
            composable(route = Screen.DetailOrder.route,
                arguments = listOf(
                    navArgument(Screen.DetailOrder.ARG_ORDER_ID) {
                        type = NavType.StringType
                    }
                )
            ) {
                DetailScreen(
                    modifier = Modifier.padding(innerPadding),
                    onBack = { navHostController.popBackStack() }
                )
            }
        }

    }
}