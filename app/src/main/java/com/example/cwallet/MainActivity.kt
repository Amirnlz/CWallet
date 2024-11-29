package com.example.cwallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.cwallet.navigation.AppNavHost
import com.example.cwallet.ui.theme.CWalletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CWalletTheme {
                val navController = rememberNavController()
                AppNavHost(
                    navHostController = navController,
                    modifier = Modifier.fillMaxSize().padding(vertical = 16.dp)
                )
            }
        }
    }
}

