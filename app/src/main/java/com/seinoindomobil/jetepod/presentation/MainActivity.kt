package com.seinoindomobil.jetepod.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seinoindomobil.jetepod.presentation.dashboard.DashboardScreen
import com.seinoindomobil.jetepod.presentation.login.HomeScreen
import com.seinoindomobil.jetepod.presentation.login.LoginScreen
import com.seinoindomobil.jetepod.presentation.onboarding.OnBoardingScreen
import com.seinoindomobil.jetepod.presentation.splashscreen.SplashScreen
import com.seinoindomobil.jetepod.presentation.theme.JetEpodTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetEpodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashScreen.route
                    ) {
                        composable(route = Screen.LoginScreen.route){
                            LoginScreen(navController)
                        }
                        composable(route = Screen.SplashScreen.route){
                            SplashScreen(navController = navController)
                        }
                        composable(route = Screen.OnBoardingScreen.route){
                            OnBoardingScreen(navController = navController)
                        }
                        composable(Screen.HomeScreen.route){
                            HomeScreen(navController =navController)
                        }
                        composable(Screen.DashboardScreen.route){
                            DashboardScreen(navController)
                        }
                    }

                }
            }
        }
    }
}
