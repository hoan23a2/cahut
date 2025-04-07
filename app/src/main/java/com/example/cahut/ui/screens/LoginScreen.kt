package com.example.cahut.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cahut.data.repository.AccountRepository
import com.example.cahut.navigation.Screen

@Composable
fun LoginScreen(navController: NavController) {
    var usernameOrEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val accountRepository = remember { AccountRepository(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Chào Mừng Trở Lại!",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = usernameOrEmail,
            onValueChange = { usernameOrEmail = it },
            label = { Text("Email hoặc Tên Đăng Nhập") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mật Khẩu") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { /* Handle forgot password */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Quên Mật Khẩu?")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Try login with email first, if fails, try with username
                val account = if (usernameOrEmail.contains("@")) {
                    accountRepository.loginAccount(usernameOrEmail, password)
                } else {
                    accountRepository.loginAccountByUsername(usernameOrEmail, password)
                }
                
                if (account != null) {
                    navController.navigate(Screen.GameLobby.route)
                } else {
                    Toast.makeText(context, "Tên đăng nhập/email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Đăng Nhập")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { navController.navigate(Screen.Register.route) }
        ) {
            Text("Chưa có tài khoản? Đăng Ký")
        }
    }
} 