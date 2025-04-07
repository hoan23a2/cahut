package com.example.cahut.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.cahut.R
import com.example.cahut.navigation.Screen

@Composable
fun CreateQuizInfoScreen(navController: NavController) {
    var quizName by remember { mutableStateOf("") }
    var quizDescription by remember { mutableStateOf("") }
    // TODO: Add image handling state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tạo Quiz Mới",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Image Selection Card
        Card(
            modifier = Modifier
                .size(200.dp)
                .clickable { /* TODO: Handle image selection */ }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Thêm Hình Ảnh",
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Quiz Name Input
        OutlinedTextField(
            value = quizName,
            onValueChange = { quizName = it },
            label = { Text("Tên Quiz") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Quiz Description Input
        OutlinedTextField(
            value = quizDescription,
            onValueChange = { quizDescription = it },
            label = { Text("Mô Tả Quiz") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            minLines = 3
        )

        Spacer(modifier = Modifier.weight(1f))

        // Next Button
        Button(
            onClick = { 
                // TODO: Save quiz info and navigate to slide creation
                navController.navigate(Screen.CreateQuizSlide.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Thêm câu hỏi")
        }
    }
} 