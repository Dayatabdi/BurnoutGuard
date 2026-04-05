package com.dayat0009.burnoutguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dayat0009.burnoutguard.navigation.SetupNavGraph
import com.dayat0009.burnoutguard.ui.theme.BurnoutGuardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BurnoutGuardTheme {

                    SetupNavGraph()

            }
        }
    }
}

