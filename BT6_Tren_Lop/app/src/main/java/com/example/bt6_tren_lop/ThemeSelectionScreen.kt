package com.example.bt6_tren_lop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun ThemeSelectionScreen(viewModel: ThemeViewModel) {
    val scope = rememberCoroutineScope()
    val savedTheme by viewModel.theme.collectAsState(initial = "Light")

    // Khi load lại app -> lấy theme từ DataStore
    var appliedTheme by remember { mutableStateOf(savedTheme) }
    var selectedTheme by remember { mutableStateOf(savedTheme) }

    // Khi DataStore có thay đổi (lúc khởi động app)
    LaunchedEffect(savedTheme) {
        appliedTheme = savedTheme
        selectedTheme = savedTheme
    }

    val bgColor = when (appliedTheme) {
        "Dark" -> Color.Black
        "Pink" -> Color(0xFFE91E63)
        "Blue" -> Color(0xFF2196F3)
        else -> Color.White
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Setting",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = if (appliedTheme == "Dark") Color.White else Color.Black
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 4 nút chọn theme
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ThemeButton("Light", selectedTheme) { selectedTheme = it }
                ThemeButton("Dark", selectedTheme) { selectedTheme = it }
                ThemeButton("Pink", selectedTheme) { selectedTheme = it }
                ThemeButton("Blue", selectedTheme) { selectedTheme = it }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = {
                appliedTheme = selectedTheme
                scope.launch { viewModel.saveTheme(selectedTheme) }
            }) {
                Text("Apply")
            }
        }
    }
}

@Composable
fun ThemeButton(name: String, selected: String, onSelect: (String) -> Unit) {
    val isSelected = selected == name
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(
                when (name) {
                    "Dark" -> Color.Black
                    "Pink" -> Color(0xFFE91E63)
                    "Blue" -> Color(0xFF2196F3)
                    else -> Color.White
                }
            )
            .clickable { onSelect(name) }
            .padding(4.dp)
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.3f))
            )
        }
    }
}
