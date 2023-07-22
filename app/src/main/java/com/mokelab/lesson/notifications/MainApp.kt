package com.mokelab.lesson.notifications

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mokelab.lesson.notification.feature.action.TapActionScreen
import com.mokelab.lesson.notification.feature.standard.StandardScreen

@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/") {
        composable("/") {
            MainScreen(toStandard = {
                navController.navigate("/standard")
            },
                toAction = {
                    navController.navigate("/action")
                }
            )
        }

        composable("/standard") {
            StandardScreen()
        }

        composable("/action") {
            TapActionScreen()
        }
    }
}