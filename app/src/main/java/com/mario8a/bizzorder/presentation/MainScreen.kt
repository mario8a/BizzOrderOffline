package com.mario8a.bizzorder.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mario8a.bizzorder.presentation.navigation.BottomNavigationBar
import com.mario8a.bizzorder.presentation.navigation.Screen

@Composable
fun MainScreen(
    navHostController: NavHostController
){
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navHostController)
        }
    ) { innerPadding ->

        NavHost(
            navController = navHostController,
            startDestination = Screen.Home.route,
        ) {
            composable(Screen.Home.route){
                HomeScreen(modifier = Modifier.padding(innerPadding))
            }
            composable(Screen.Create .route){
                HomeScreen(modifier = Modifier.padding(innerPadding))
            }
            composable(Screen.PreOrders.route){
                HomeScreen(modifier = Modifier.padding(innerPadding))
            }
        }

    }
}