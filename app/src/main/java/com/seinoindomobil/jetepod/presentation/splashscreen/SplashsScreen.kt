package com.seinoindomobil.jetepod.presentation.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seinoindomobil.jetepod.R
import com.seinoindomobil.jetepod.presentation.login.LoginViewModel
import com.seinoindomobil.jetepod.presentation.theme.Poppins
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viwmodel: LoginViewModel = hiltViewModel()
) {
    var startAnimation by remember { mutableStateOf(false) }
    val scaleAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.7f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
    )
    val loginDataStore = viwmodel._hasLogin

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000L)
        navController.popBackStack()

        if (loginDataStore !=null) {
            navController.navigate("dashboard_screen")
        } else {
            navController.navigate("onboarding_screen")
        }
    }
    Splash(scaleAnimation)
}

@Composable
fun Splash(scale: Float = 0f) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_splash),
            contentDescription = "back-ground",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Image(
            painter = painterResource(id = R.drawable.img_logo_splash),
            contentDescription = "logo splash",
            modifier = Modifier
                .scale(scale)
                .align(Alignment.Center)
                .offset(y = (-80).dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .offset(y = 50.dp)
                .wrapContentSize()
        ) {
            Text(
                stringResource(id = R.string.TitleSplash),
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.White
            )
            Text(
                stringResource(id = R.string.SubTitleSplash),
                fontFamily = Poppins,
                fontWeight = FontWeight.Thin,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.offset(y = (-10).dp)
            )
        }
        Text(
            stringResource(id = R.string.CreatorSplash),
            fontFamily = Poppins,
            fontWeight = FontWeight.Thin,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        )

    }
}
