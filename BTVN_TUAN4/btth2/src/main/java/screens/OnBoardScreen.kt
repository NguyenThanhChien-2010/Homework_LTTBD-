package com.example.btth2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.btth2.model.slides

@Composable
fun OnBoardScreen(navController: NavController) {
    var page by remember { mutableStateOf(0) }
    val slide = slides[page]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // ---- IMAGE ----
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = slide.image),
            contentDescription = slide.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        )

        // ---- TEXT ----
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = slide.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = slide.description,
                color = Color.Gray,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // ---- DOT INDICATOR ----
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            slides.indices.forEach { i ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (i == page) 12.dp else 8.dp)
                        .clip(CircleShape)
                        .background(if (i == page) Color(0xFF0D47A1) else Color.LightGray)
                )
            }
        }

        // ---- BUTTONS (Back + Next) ----
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (page > 0) {
                IconButton(
                    onClick = { page-- },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE3F2FD))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF0D47A1)
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp)) // giữ bố cục cân
            }

            Button(
                onClick = {
                    if (page < slides.lastIndex) page++
                    else navController.navigate("home") {
                        popUpTo("onboard") { inclusive = true }
                    }
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = if (page < slides.lastIndex) "Next" else "Get Started",
                    fontSize = 18.sp
                )
            }
        }
    }
}
