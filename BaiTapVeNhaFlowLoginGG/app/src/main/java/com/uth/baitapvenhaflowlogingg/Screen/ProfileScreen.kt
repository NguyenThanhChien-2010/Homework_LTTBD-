package com.uth.baitapvenhaflowlogingg

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(
    googleSignInClient: GoogleSignInClient,
    navController: NavHostController,
    name: String?,
    email: String?,
    photoUrl: String?
) {
    val dateOfBirth = remember { mutableStateOf("11/08/2005") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Header với nút Back và tiêu đề
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    googleSignInClient.signOut().addOnCompleteListener {
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF00AEEF),
                    modifier = Modifier.size(40.dp)
                )
            }

            Text(
                text = "Profile",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00AEEF),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Ảnh đại diện
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.BottomEnd
        ) {
            if (!photoUrl.isNullOrEmpty() && photoUrl != "null") {
                Image(
                    painter = rememberAsyncImagePainter(photoUrl),
                    contentDescription = "User Photo",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Default user",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    tint = Color.White
                )
            }

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Change photo",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .background(Color(0xFF00AEEF), CircleShape)
                    .clip(CircleShape)
                    .padding(4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Form thông tin
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Name",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            OutlinedTextField(
                value = name ?: "Không có tên",
                onValueChange = {},
                label = { Text("") },
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Email",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            OutlinedTextField(
                value = email ?: "Không có email",
                onValueChange = {},
                label = { Text("") },
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Date of Birth",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = dateOfBirth.value,
                onValueChange = { dateOfBirth.value = it },
                label = { Text("") },
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Dropdown arrow",
                        modifier = Modifier.rotate(270f)
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Nút Back
        Button(
            onClick = {
                googleSignInClient.signOut().addOnCompleteListener {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00AEEF)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Đăng xuất", color = Color.White, fontSize = 18.sp)
        }
    }
}