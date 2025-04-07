package com.example.cahut.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cahut.navigation.Screen

@Composable
fun PlayQuizScreen(navController: NavController) {
    var selectedAnswer by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Timer and Progress
        LinearProgressIndicator(
            progress = 0.7f,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Question Counter
        Text(
            text = "Câu hỏi 1/10",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Question Text
        Text(
            text = "Thủ đô của Việt Nam là gì?",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Answer Options
        val options = listOf(
            "A" to "Hà Nội",
            "B" to "Hồ Chí Minh",
            "C" to "Đà Nẵng",
            "D" to "Hải Phòng"
        )

        options.forEach { (option, answer) ->
            val isSelected = selectedAnswer == option

            OutlinedButton(
                onClick = { selectedAnswer = option },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(56.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isSelected) 
                        MaterialTheme.colorScheme.primaryContainer 
                    else 
                        MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    width = 2.dp,
                    color = if (isSelected) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.outline
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "$option. $answer",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Submit Button
        Button(
            onClick = { navController.navigate(Screen.QuizScore.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = selectedAnswer != null
        ) {
            Text(
                text = "Trả lời",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
} 