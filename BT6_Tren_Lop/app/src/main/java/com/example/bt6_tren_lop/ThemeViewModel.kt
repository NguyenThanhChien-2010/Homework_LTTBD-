package com.example.bt6_tren_lop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(private val pref: ThemePreference) : ViewModel() {

    val theme = pref.getTheme.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        "Light"
    )

    fun saveTheme(theme: String) {
        viewModelScope.launch {
            pref.saveTheme(theme)
        }
    }
}
