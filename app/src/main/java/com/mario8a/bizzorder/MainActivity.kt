package com.mario8a.bizzorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mario8a.bizzorder.presentation.HomeScreen
import com.mario8a.bizzorder.presentation.MainScreen
import com.mario8a.bizzorder.ui.theme.BizzOrderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BizzOrderTheme {
                val navController = rememberNavController()
                MainScreen(navController)
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    HomeScreen(modifier = Modifier.padding(innerPadding))
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BizzOrderTheme {
        Greeting("Android")
    }
}