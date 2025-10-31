package com.uth.baitapvenhaflowlogingg

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        println("DEBUG: SignInLauncher result received - ResultCode: ${result.resultCode}")
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            handleSignInResult(data)
        } else {
            println("DEBUG: SignIn cancelled or failed")
        }
    }

    private fun handleSignInResult(data: Intent?) {
        println("DEBUG: handleSignInResult started")
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            println("DEBUG: Google account obtained - Email: ${account?.email}")

            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    println("DEBUG: Firebase auth successful - User: ${user?.email}")
                    // Navigation will be handled by LaunchedEffect observing auth state
                } else {
                    println("DEBUG: Firebase auth failed - ${task.exception?.message}")
                }
            }
        } catch (e: Exception) {
            println("DEBUG: Error in handleSignInResult - ${e.message}")
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            SmartTasksApp(auth, googleSignInClient, signInLauncher)
        }
    }
}

@Composable
fun SmartTasksApp(
    auth: FirebaseAuth,
    googleSignInClient: GoogleSignInClient,
    signInLauncher: androidx.activity.result.ActivityResultLauncher<Intent>
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    // Xử lý khi auth state thay đổi - ĐÂY LÀ QUAN TRỌNG NHẤT
    LaunchedEffect(auth.currentUser) {
        val user = auth.currentUser
        if (user != null) {
            println("DEBUG: Auth state changed - Navigating to profile: ${user.email}")
            navController.navigate(
                "home/${urlEncode(user.displayName ?: "")}/${urlEncode(user.email ?: "")}/${urlEncode(user.photoUrl?.toString() ?: "")}"
            ) {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(googleSignInClient, signInLauncher)
        }
        composable("home/{name}/{email}/{photoUrl}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")?.let { urlDecode(it) }
            val email = backStackEntry.arguments?.getString("email")?.let { urlDecode(it) }
            val photoUrl = backStackEntry.arguments?.getString("photoUrl")?.let { urlDecode(it) }
            ProfileScreen(googleSignInClient, navController, name, email, photoUrl)
        }
    }
}

fun urlEncode(value: String?): String {
    return URLEncoder.encode(value ?: "", StandardCharsets.UTF_8.toString()).replace("+", "%20")
}

fun urlDecode(value: String?): String {
    return Uri.decode(value ?: "")
}