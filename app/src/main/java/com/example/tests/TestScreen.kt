package com.example.tests

import android.os.CountDownTimer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.saveable.*

val mapSaver = Saver<MutableMap<Int, String?>, Map<Int, String?>>(
    save = { state -> state.toMap() },
    restore = { map -> mutableMapOf(*map.toList().toTypedArray()) }
)

@Composable
fun TestScreen(navController: NavHostController, testId: String, language: String) {
    val questions = remember { TestQuestions.getQuestions(testId, language) }
    var currentIndex by rememberSaveable { mutableStateOf(0) }
    var score by rememberSaveable { mutableStateOf(0) }
    var selectedAnswer by rememberSaveable { mutableStateOf<String?>(null) }

    val userAnswers = rememberSaveable(saver = mapSaver) { mutableMapOf<Int, String?>() }

    var timerText by rememberSaveable { mutableStateOf("05:00") }

    // Таймер
    LaunchedEffect(Unit) {
        object : CountDownTimer(5 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timerText = String.format("%02d:%02d", minutes, seconds)
            }
            override fun onFinish() {
                saveResultsToFirebase(testId, userAnswers, score, language)
                navController.navigate("testResult/$testId/$score/$language")
            }
        }.start()
    }

    if (questions.isEmpty()) {
        Text("No questions available", modifier = Modifier.padding(16.dp))
        return
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Timer: $timerText")
            Spacer(modifier = Modifier.height(48.dp))
        }

        item {
            Text(text = questions[currentIndex].question, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(questions[currentIndex].options.size) { index ->
            val answer = questions[currentIndex].options[index]
            Button(
                onClick = {
                    selectedAnswer = answer
                    userAnswers[currentIndex] = answer
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedAnswer == answer)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(text = answer)
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                if (currentIndex > 0) {
                    Button(onClick = { currentIndex-- }) { Text("Previous") }
                }

                if (currentIndex < questions.lastIndex) {
                    Button(onClick = { currentIndex++ }) { Text("Next") }
                } else {
                    Button(onClick = {
                        val correctAnswers = questions.map { it.correctAnswer }
                        score = userAnswers.count { (index, answer) ->
                            correctAnswers.getOrNull(index) == answer
                        }
                        saveResultsToFirebase(testId, userAnswers, score, language)
                        navController.navigate("testResult/$testId/$score/$language")
                    }) { Text("Finish") }
                }
            }
        }
    }
}

fun saveResultsToFirebase(testId: String, answers: Map<Int, String?>, score: Int, language: String) {
    val firestore = FirebaseFirestore.getInstance()
    val stringAnswers = answers.mapKeys { it.key.toString() }
    val resultData = hashMapOf(
        "answers" to stringAnswers,
        "score" to score
    )

    firestore.collection("testsResult")
        .document(language)
        .collection(testId)
        .document("results")
        .set(resultData)
        .addOnSuccessListener {
            println("Results saved successfully")
        }
        .addOnFailureListener { e ->
            println("Error saving results: $e")
        }
}

