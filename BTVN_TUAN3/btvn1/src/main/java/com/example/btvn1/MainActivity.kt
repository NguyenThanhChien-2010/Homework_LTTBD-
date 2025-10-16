package com.example.btvn1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.example.btvn1.ui.theme.BTVN_TUAN3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTVN_TUAN3Theme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("components") { ComponentsListScreen(navController) }
        composable("textdetail") { TextDetailScreen(navController) }
        composable("image") { ImageScreen(navController) }
        composable("textfield") { TextFieldScreen(navController) }
        composable("rowlayout") { RowLayoutScreen(navController) }
    }
}

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.compose_logo),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 32.dp)
        )

        Text(
            text = "Jetpack Compose",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = { navController.navigate("components") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "I'm ready", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun ComponentsListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "UI Components List",
            color = Color(0xFF2196F3),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        Text("Display", fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp, start = 4.dp))
        ComponentItem("Text", "Displays text") { navController.navigate("textdetail") }
        ComponentItem("Image", "Displays an image") { navController.navigate("image") }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Input", fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp, start = 4.dp))
        ComponentItem("TextField", "Input field for text") { navController.navigate("textfield") }
        ComponentItem("PasswordField", "Input field for passwords")

        Spacer(modifier = Modifier.height(16.dp))
        Text("Layout", fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp, start = 4.dp))
        ComponentItem("Column", "Arranges elements vertically")
        ComponentItem("Row", "Arranges elements horizontally") { navController.navigate("rowlayout") }
    }
}

@Composable
fun ComponentItem(title: String, desc: String, color: Color = Color(0xFFE3F2FD), onClick: (() -> Unit)? = null) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(enabled = onClick != null) { onClick?.invoke() },
        shape = RoundedCornerShape(8.dp),
        color = color
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = desc, color = Color.Gray, fontSize = 13.sp)
        }
    }
}

@Composable
fun TextDetailScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color(0xFF2196F3)
                )
            }

            Text(
                text = "Text Detail",
                color = Color(0xFF2196F3),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val styledText = buildAnnotatedString {
                withStyle(SpanStyle(color = Color.Black)) { append("The ") }
                withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) { append("quick ") }
                withStyle(SpanStyle(color = Color(0xFF795548), fontWeight = FontWeight.Bold)) { append("Brown ") }
                withStyle(SpanStyle(color = Color.Black)) { append("fox ") }
                withStyle(SpanStyle(letterSpacing = 2.sp)) { append("jumps ") }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("over ") }
                withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) { append("the ") }
                withStyle(SpanStyle(color = Color.Gray)) { append("lazy dog.") }
            }

            Text(
                text = styledText,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ImageScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF2196F3)
                )
            }

            Text(
                text = "Images",
                color = Color(0xFF2196F3),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        AsyncImage(
            model = "https://s.cmx-cdn.com/giaothongvantaitphcm.edu.vn/wp-content/uploads/2024/06/ky-niem-36-nam-thanh-lap-truong-dai-hoc-giao-thong-van-tai-tphcm-560px.jpg",
            contentDescription = "Logo UTH",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "https://s.cmx-cdn.com/giaothongvantaitphcm.edu.vn/wp-content/uploads/2024/06/ky-niem-36-nam-thanh-lap-truong-dai-hoc-giao-thong-van-tai-tphcm-560px.jpg",
            color = Color(0xFF2196F3),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.uth_building),
            contentDescription = "UTH Building",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(
            text = "In App",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
    }
}

@Composable
fun TextFieldScreen(navController: NavHostController? = null) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { navController?.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF2196F3)
                )
            }

            Text(
                text = "TextField",
                color = Color(0xFF2196F3),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Thông tin nhập") },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Tự động cập nhật dữ liệu theo TextField",
                color = Color.Red,
                modifier = Modifier.padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun RowLayoutScreen(navController: NavHostController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { navController?.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Quay lại",
                    tint = Color(0xFF2196F3)
                )
            }

            Text(
                text = "Row Layout",
                color = Color(0xFF2196F3),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(4) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    repeat(3) { idx ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1.8f)
                                .background(
                                    if (idx == 1) Color(0xFF2196F3) else Color(0xFFBBDEFB),
                                    RoundedCornerShape(12.dp)
                                )
                        )
                    }
                }
            }
        }
    }
}

