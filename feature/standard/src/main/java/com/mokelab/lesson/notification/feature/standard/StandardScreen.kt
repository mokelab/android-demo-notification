package com.mokelab.lesson.notification.feature.standard

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardScreen() {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { accepted ->
        if (accepted) {
            showNotification(context)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Standard") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Button(onClick = {
                prepareNotification(context, launcher) {
                    showNotification(context)
                }
            }) {
                Text("Show notification")
            }
        }
    }
}

private fun prepareNotification(
    context: Context,
    launcher: ActivityResultLauncher<String>,
    next: () -> Unit,
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
            return
        }
    }
    next()
}

@SuppressLint("MissingPermission")
private fun showNotification(context: Context) {
    val manager = NotificationManagerCompat.from(context)
    // create channel
    val channel = NotificationChannelCompat.Builder(
        "channel_id",
        NotificationManagerCompat.IMPORTANCE_HIGH,
    )
        .setName("channel_name")
        .build()
    manager.createNotificationChannel(channel)

    // create notification
    val notification = NotificationCompat.Builder(
        context, "channel_id",
    )
        .setSmallIcon(R.drawable.ic_android_black_24dp)
        .setContentTitle("Title")
        .setContentText("Content")
        .build()

    // show notification
    manager.notify(1, notification)
}
