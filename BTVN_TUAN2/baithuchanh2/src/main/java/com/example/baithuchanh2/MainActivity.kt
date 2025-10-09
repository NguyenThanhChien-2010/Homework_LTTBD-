package com.example.baithuchanh2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baithuchanh2.ui.theme.BTVN_TUAN2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTVN_TUAN2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EmailCheckScreen()
                }
            }
        }
    }
}

@Composable
fun EmailCheckScreen() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Red) }

    // Toàn bộ giao diện căn giữa màn hình
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Thực hành 02",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Ô nhập email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        // Hiển thị thông báo
        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = messageColor,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút kiểm tra email
        Button(
            onClick = {
                val text = email.text.trim()
                when {
                    text.isEmpty() -> {
                        message = "Email không hợp lệ"
                        messageColor = Color.Red
                    }
                    !text.contains("@") -> {
                        message = "Email không đúng định dạng"
                        messageColor = Color.Red
                    }
                    else -> {
                        message = "Bạn đã nhập email hợp lệ"
                        messageColor = Color(0xFF4CAF50)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kiểm tra", color = Color.White)
        }
    }
}
