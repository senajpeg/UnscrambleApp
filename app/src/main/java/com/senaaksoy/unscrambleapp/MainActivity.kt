package com.senaaksoy.unscrambleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.senaaksoy.unscrambleapp.screens.GameScreen
import com.senaaksoy.unscrambleapp.ui.theme.UnscrambleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnscrambleAppTheme {

                GameScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UnscrambleAppPreview() {
    GameScreen()
}