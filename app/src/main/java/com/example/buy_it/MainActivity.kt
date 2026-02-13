package com.example.buy_it

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.buy_it.ui.EditInfo
import com.example.buy_it.ui.Login
import com.example.buy_it.ui.Profile
import com.example.buy_it.ui.Register

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Profile()
        }
    }
}