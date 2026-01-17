package com.example.tests

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import java.util.Calendar
import coil.compose.AsyncImage
import androidx.compose.material3.*
import com.example.tests.BuildConfig

@Composable
fun MainScreen(navController: NavHostController, onNavigateToTestSelection: (String) -> Unit) {
    var selectedHour by remember { mutableStateOf(12) }
    var selectedMinute by remember { mutableStateOf(0) }
    val context = LocalContext.current

    var gifUrl by remember { mutableStateOf<String?>(null) }
    val apiKey = BuildConfig.GIPHY_API_KEY

    LaunchedEffect(Unit) {
        try {
            val response = GiphyApi.service.getRandomGif(apiKey, "learning", 3)
            gifUrl = response.data.firstOrNull()?.images?.downsized_medium?.url
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
            onClick = {
                val context = navController.context
                showTimePickerDialog(context) { hour, minute ->
                    selectedHour = hour
                    selectedMinute = minute
                    scheduleNotification(context, hour, minute)
                    Toast.makeText(
                        context,
                        "Notification set for $hour:$minute",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
                .align(Alignment.End)
                .background(Color.Gray)
        ) {
            Text("Remind me", color = Color.White)
        }

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "TESTS",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = {
                onNavigateToTestSelection("english")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.Black)
        ) {
            Text("English", color = Color.White, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = {
                onNavigateToTestSelection("polish")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.Black)
        ) {
            Text("Polish", color = Color.White, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(40.dp))

        gifUrl?.let { url ->
            AsyncImage(
                model = gifUrl,
                contentDescription = "Random Learning Gif",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(8.dp)
            )
        } ?: Text(text = "Loading Gif...", color = Color.Gray)

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Music for passing tests:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_2),
                contentDescription = "Music Icon",
                modifier = Modifier
                    .size(75.dp)
                    .padding(8.dp)
                    .clickable {
                        val context = navController.context
                        val intent = Intent(Intent.ACTION_MAIN).apply {
                            addCategory(Intent.CATEGORY_APP_MUSIC)
                        }
                        context.startActivity(intent)
                    }
            )
        }
    }
}

fun showTimePickerDialog(context: Context, onTimeSelected: (Int, Int) -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    android.app.TimePickerDialog(
        context,
        { _, selectedHour, selectedMinute ->
            onTimeSelected(selectedHour, selectedMinute)
        },
        hour,
        minute,
        true
    ).show()
}

fun scheduleNotification(context: Context, hour: Int, minute: Int) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, NotificationReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
    }

    if (calendar.timeInMillis < System.currentTimeMillis()) {
        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    } else {
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}
