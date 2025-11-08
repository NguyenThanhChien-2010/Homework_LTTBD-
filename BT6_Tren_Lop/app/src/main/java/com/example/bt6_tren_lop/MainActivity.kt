package com.example.bt6_tren_lop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.bt6_tren_lop.ui.theme.BT6_Tren_LopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = ThemePreference(applicationContext)
        val factory = ThemeViewModelFactory(pref)
        val viewModel = ViewModelProvider(this, factory)[ThemeViewModel::class.java]

        setContent {
            BT6_Tren_LopTheme {
                ThemeSelectionScreen(viewModel)
            }
        }
    }
}
