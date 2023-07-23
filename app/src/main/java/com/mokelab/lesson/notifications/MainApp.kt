package com.mokelab.lesson.notifications

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mokelab.lesson.notification.feature.action.TapActionScreen
import com.mokelab.lesson.notification.feature.standard.StandardScreen

@Composable
fun MainApp(
    notificationData: Bundle?
) {
    val context = LocalContext.current
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/") {
        composable("/") {
            MainScreen(
                toStandard = {
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
            TapActionScreen(createPendingIntent = {
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("key", "fromNotification")
                }
                PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE,
                )
            })
        }
    }

    LaunchedEffect(notificationData) {
        println("data=$notificationData")
        if (notificationData == null) return@LaunchedEffect
        println(notificationData)
    }
}