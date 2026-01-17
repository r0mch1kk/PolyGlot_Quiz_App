package com.example.tests

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.compose.material3.*
import androidx.compose.runtime.*


@Composable
fun TestResultScreen(testId: String, score: Int, language: String, onNavigate: (String) -> Unit) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val notificationId = 1
        val channelId = "test_results_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Test Results"
            val descriptionText = "Notifications about test results"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Test Result")
            .setContentText(if (score > 3) "The test is passed" else "The test isn't passed")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(context).notify(notificationId, notification)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Your score: $score",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = if (score > 3) "Passed" else "Failed",
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
            color = if (score > 3) Color.Green else Color.Red,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = { onNavigate("main") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Go to main", color = Color.White)
        }

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = { onNavigate("checkResult/$testId/$language/$score") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Check Answers", color = Color.White)
        }
    }
}
