package com.example.tests

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController) { language ->
                navController.navigate("testSelection/$language")
            }
        }

        composable("testSelection/{language}") { backStackEntry ->
            val language = backStackEntry.arguments?.getString("language") ?: "english"
            TestSelectionScreen(
                navController = navController,
                language = language
            ) { testId, selectedLanguage ->
                navController.navigate("beforeTest/$testId/$selectedLanguage")
            }
        }

        composable("beforeTest/{testId}/{language}") { backStackEntry ->
            val testId = backStackEntry.arguments?.getString("testId") ?: "Test1"
            val language = backStackEntry.arguments?.getString("language") ?: "english"
            BeforeTestScreen(
                navController = navController,
                testId = testId,
                language = language
            )
        }

        composable("test/{testId}/{language}") { backStackEntry ->
            val testId = backStackEntry.arguments?.getString("testId") ?: "Test1"
            val language = backStackEntry.arguments?.getString("language") ?: "english"
            TestScreen(
                navController = navController,
                testId = testId,
                language = language
            )
        }

        composable("testResult/{testId}/{score}/{language}") { backStackEntry ->
            val testId = backStackEntry.arguments?.getString("testId") ?: "Test1"
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            val language = backStackEntry.arguments?.getString("language") ?: "english"
            TestResultScreen(
                testId = testId,
                score = score,
                language = language,
                onNavigate = { destination ->
                    navController.navigate(destination)
                }
            )
        }

        composable("checkResult/{testId}/{language}/{score}") { backStackEntry ->
            val testId = backStackEntry.arguments?.getString("testId") ?: "Test1"
            val language = backStackEntry.arguments?.getString("language") ?: "english"
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            CheckResultScreen(
                testId = testId,
                language = language,
                score = score,
                navController = navController)
        }
    }
}
