package com.maxdgf.top15randomcatfacts


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.maxdgf.top15randomcatfacts.presentation.common.theme.Top15RandomCatFactsTheme
import com.maxdgf.top15randomcatfacts.presentation.main_screen.MainScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Top15RandomCatFactsTheme {
                MainScreen()
            }
        }
    }
}