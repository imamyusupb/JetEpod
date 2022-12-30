package com.seinoindomobil.jetepod.presentation

sealed class Screen(val route :String){
    object LoginScreen :Screen("login_screen")
    object HomeScreen :Screen("home_screen")
    object SplashScreen:Screen("splash_screen")
    object OnBoardingScreen:Screen("onboarding_screen")
    object DashboardScreen:Screen("dashboard_screen")
}
