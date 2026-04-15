package com.example.aplicacion1udec

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.aplicacion1udec.ui.PerfilView
import com.example.aplicacion1udec.theme.Aplicacion1UdecTheme

//import com.example.aplicacion1udec.Screen.Inicio

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Aplicacion1UdecTheme {
                PerfilView()
            }
        }
    }
}

