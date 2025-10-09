package com.example.baithuchanh1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.baithuchanh1.ui.theme.BTVN_TUAN2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTVN_TUAN2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NumberScreen()
                }
            }
        }
    }
}

@Composable
fun NumberScreen() {
    var inputText by remember { mutableStateOf(TextFieldValue("")) }
    var errorText by remember { mutableStateOf("") }
    var numberList by remember { mutableStateOf(listOf<Int>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(
            text = "Thực hành 02",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Nhập vào số lượng") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    val text = inputText.text.trim()
                    if (text.isEmpty() || text.toIntOrNull() == null) {
                        errorText = "Dữ liệu bạn nhập không hợp lệ"
                        numberList = emptyList()
                    } else {
                        val n = text.toInt()
                        errorText = ""
                        numberList = (1..n).toList()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text("Tạo")
            }
        }

        if (errorText.isNotEmpty()) {
            Text(
                text = errorText,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Danh sách các số
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            numberList.forEach { num ->
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = num.toString(),
                        color = Color.White
                    )
                }
            }
        }
    }
}
