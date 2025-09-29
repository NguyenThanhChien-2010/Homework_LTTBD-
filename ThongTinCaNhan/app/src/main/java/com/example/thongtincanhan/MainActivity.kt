package com.example.thongtincanhan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thongtincanhan.ui.theme.ThongTinCaNhanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThongTinCaNhanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hàng chứa 2 nút Back và Edit
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SquareIconButton(onClick = { /* TODO: back */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            SquareIconButton(onClick = { /* TODO: edit */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "Edit",
                    tint = Color(0xFF4CAF50) // xanh lá
                )
            }
        }

        Spacer(modifier = Modifier.height(80.dp))

        // Avatar tròn
        Image(
            painter = painterResource(id = R.drawable.avatar_placeholder),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(3.dp, Color.LightGray, CircleShape)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Tên
        Text(
            text = "Johan Smith",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Địa chỉ
        Text(
            text = "California, USA",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun SquareIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, Color.LightGray, MaterialTheme.shapes.small)
            .clip(MaterialTheme.shapes.small)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize()
        ) {
            icon()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfile() {
    ThongTinCaNhanTheme {
        ProfileScreen()
    }
}
