package com.example.baithuchanh3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baithuchanh3.ui.theme.BTVN_TUAN2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTVN_TUAN2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KiemTraTuoiScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun KiemTraTuoiScreen(modifier: Modifier = Modifier) {
    var hoTen by remember { mutableStateOf(TextFieldValue("")) }
    var tuoi by remember { mutableStateOf(TextFieldValue("")) }
    var ketQua by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Thực Hành 01",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Column(
            modifier = Modifier
                .background(Color(0xFFEFEFEF), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = hoTen,
                onValueChange = { hoTen = it },
                label = { Text("Họ và tên") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = tuoi,
                onValueChange = { tuoi = it },
                label = { Text("Tuổi") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val age = tuoi.text.toIntOrNull()
                ketQua = if (hoTen.text.isBlank() || age == null) {
                    "Vui lòng nhập đúng thông tin!"
                } else {
                    when {
                        age < 2 -> "${hoTen.text} là Em bé"
                        age in 2..6 -> "${hoTen.text} là Trẻ em"
                        age in 7..65 -> "${hoTen.text} là Người lớn"
                        else -> "${hoTen.text} là Người già"
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
        ) {
            Text(text = "Kiểm tra", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = ketQua,
            fontSize = 20.sp,
            color = Color(0xFF333333),
            fontWeight = FontWeight.Medium
        )
    }
}


