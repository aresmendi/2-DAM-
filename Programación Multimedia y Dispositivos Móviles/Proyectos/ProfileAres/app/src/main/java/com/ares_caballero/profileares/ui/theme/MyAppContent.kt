package com.ares_caballero.profileares.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ares_caballero.profileares.ui.screens.Profile

@Composable
fun MyAppContent(){
    ProfileAresTheme {
        Profile()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyAppContentPreview(){
    MyAppContent()
}