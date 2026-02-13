package com.ares_caballero.contadordeclics
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ares_caballero.contadordeclics.ui.screens.ClickCounter
import com.ares_caballero.contadordeclics.ui.theme.MyAppContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppContent {
                ClickCounter()
            }
        }
    }
}



