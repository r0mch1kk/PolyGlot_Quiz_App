package com.example.tests

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun BeforeTestScreen(navController: NavHostController, testId: String, language: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.image_1),
            contentDescription = "Back to Main",
            modifier = Modifier
                .size(56.dp)
                .padding(8.dp)
                .align(Alignment.Start)
                .clickable {
                    navController.navigate("main")
                }
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Information about $testId - $language",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(150.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Duration:",
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "5 minutes",
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Number of questions:",
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "6",
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(154.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = {
                navController.navigate("test/$testId/$language")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.Black)
        ) {
            Text(text = "START!")
        }
    }
}
