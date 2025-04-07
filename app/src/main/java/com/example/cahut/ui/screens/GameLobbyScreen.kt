package com.example.cahut.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cahut.ui.theme.GameLobbyTheme
import com.example.cahut.navigation.Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.foundation.clickable
import kotlinx.coroutines.launch

@Composable
fun GameLobbyScreen(navController: NavController) {
    var gameRoomId by remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Menu",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Divider()
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Edit, contentDescription = "Chỉnh Sửa Hồ Sơ") },
                    label = { Text("Chỉnh Sửa Hồ Sơ") },
                    selected = false,
                    onClick = { 
                        scope.launch {
                            drawerState.close()
                            // Handle edit profile navigation
                        }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Danh Sách Quiz") },
                    label = { Text("Danh Sách Quiz") },
                    selected = false,
                    onClick = { 
                        scope.launch {
                            drawerState.close()
                            // Handle quiz list navigation
                        }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Đăng Xuất") },
                    label = { Text("Đăng Xuất") },
                    selected = false,
                    onClick = { 
                        scope.launch {
                            drawerState.close()
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.GameLobby.route) { inclusive = true }
                            }
                        }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with Avatar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Hồ Sơ",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { scope.launch { drawerState.open() } },
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Cahut",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.size(40.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Game Room Input
            OutlinedTextField(
                value = gameRoomId,
                onValueChange = { gameRoomId = it },
                label = { Text("Nhập Mã Phòng") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Join Game Button
            Button(
                onClick = { 
                    navController.navigate(Screen.PlayQuiz.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Tham Gia")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Host Game Button
            Button(
                onClick = { /* Handle create game */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Tạo Phòng")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Create Quiz Button
            Button(
                onClick = { navController.navigate(Screen.CreateQuizInfo.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            ) {
                Text("Tạo Quiz")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Recent Games Section
            Text(
                text = "Phòng Gần Đây",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Placeholder for recent games list
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Chưa có phòng nào",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Debug Button
            Button(
                onClick = { navController.navigate(Screen.DatabaseDebug.route) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Xem Dữ Liệu")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameLobbyScreenPreview() {
    GameLobbyTheme {
        GameLobbyScreen(navController = rememberNavController())
    }
} 