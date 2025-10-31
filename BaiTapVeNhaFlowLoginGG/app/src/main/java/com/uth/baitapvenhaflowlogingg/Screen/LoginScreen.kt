package com.uth.baitapvenhaflowlogingg

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignInClient

@Composable
fun LoginScreen(
    googleSignInClient: GoogleSignInClient,
    signInLauncher: androidx.activity.result.ActivityResultLauncher<Intent>
) {
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(id = R.drawable.logo_uth),
                contentDescription = "UTH Logo"
            )
            Text(
                text = "SmartTasks",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "A Simple and efficient to-do-app",
                fontSize = 18.sp,
                color = Color(0xFF2196F3)
            )
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                text = "Welcome",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Ready to explore? Log in to get started",
                fontSize = 20.sp,
            )
        }

        Button(
            onClick = {
                googleSignInClient.signOut().addOnCompleteListener {
                    val signInIntent = googleSignInClient.signInIntent
                    signInLauncher.launch(signInIntent)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(70.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "SIGN IN WITH GOOGLE",
                    color = Color.White,
                    fontSize = 20.sp,
                )
            }
        }
    }
}