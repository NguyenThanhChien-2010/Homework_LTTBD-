package com.example.glassmorphism

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompareGlassmorphism()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareGlassmorphism() {
    var isApplied by remember { mutableStateOf(false) }

    // ✅ Nền trắng tinh
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            // --- Tiêu đề ---
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🎨",
                        fontSize = 40.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Glassmorphism Demo",
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }

            // --- Nút bật / tắt hiệu ứng ---
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Hiệu ứng Glassmorphism",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = if (isApplied) "Đang bật" else "Đang tắt",
                            color = if (isApplied) Color(0xFF4CAF50) else Color(0xFFF44336),
                            fontSize = 14.sp
                        )
                    }
                    Button(
                        onClick = { isApplied = !isApplied },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isApplied) Color(0xFF4CAF50) else Color(0xFF2196F3)
                        ),
                        modifier = Modifier.height(48.dp)
                    ) {
                        Text(
                            text = if (isApplied) "TẮT HIỆU ỨNG" else "BẬT HIỆU ỨNG",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // so sánh
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Box(modifier = Modifier.weight(1f)) {
                    if (!isApplied) {
                        NormalCard()
                    } else {
                        PlaceholderCard(
                            title = "Thiết kế thường",
                            description = "Đã được thay thế bằng Glassmorphism",
                            isGlass = false
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Box(modifier = Modifier.weight(1f)) {
                    if (isApplied) {
                        GlassCardApplied()
                    } else {
                        PlaceholderCard(
                            title = "Glassmorphism",
                            description = "Sẽ xuất hiện khi bật hiệu ứng",
                            isGlass = true
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NormalCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE3F2FD))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📱",
                    fontSize = 24.sp
                )
            }

            Text(
                text = "Thiết kế thường",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            Text(
                text = "• Nền đặc màu\n• Không có độ trong suốt\n• Hiệu ứng bóng cơ bản\n• Phong cách truyền thống",
                color = Color.Gray,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = Color(0xFF1976D2),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "ĐANG HIỂN THỊ",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun GlassCardApplied() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true
            ),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD).copy(alpha = 0.5f)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFBBDEFB).copy(alpha = 0.6f))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "✨",
                    fontSize = 24.sp
                )
            }

            Text(
                text = "Glassmorphism",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            Text(
                text = "• Nền trong suốt nhẹ\n• Hiệu ứng mờ mềm mại\n• Viền sáng tinh tế\n• Tạo chiều sâu không gian",
                color = Color.DarkGray,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = Color(0xFF90CAF9),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "ĐANG HIỂN THỊ",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun PlaceholderCard(title: String, description: String, isGlass: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isGlass)
                Color(0xFFBBDEFB).copy(alpha = 0.2f)
            else
                Color(0xFFE0E0E0)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Text(
                text = description,
                color = Color.Gray,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
