package com.ares_caballero.profileares.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ares_caballero.profileares.ui.shared.MyBottomBar
import com.ares_caballero.profileares.ui.shared.MyTopAppBar
import com.ares_caballero.profileares.R
import com.ares_caballero.profileares.ui.shared.MyImage
import com.ares_caballero.profileares.ui.shared.MyImages
import com.ares_caballero.profileares.ui.shared.MyList
@Suppress("SpellCheckingInspection")
@Composable
fun Profile(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { MyTopAppBar() },
        bottomBar = { MyBottomBar() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row {
                    MyImage(image = R.drawable.my_profile)
                }

                Spacer(modifier = Modifier.height(20.dp))

                HorizontalDivider(thickness = 1.dp)

                Spacer(modifier = Modifier.height(12.dp))

                MyList()

                HorizontalDivider(thickness = 1.dp, color = DividerDefaults.color)
                Spacer(modifier = Modifier.height(12.dp))

                Spacer(modifier = Modifier.height(12.dp))

                MyImages(image = "https://media.istockphoto.com/id/1489790715/es/foto/prados-y-caba%C3%B1as-r%C3%BAsticas-de-piedra-en-hermoso-valle-verde.jpg?s=1024x1024&w=is&k=20&c=-e8tgz1PO3bICL-tSfXo1SmfwO9zpmrttYZGL84MRq4=", "Finlandia sin hielo")

                MyImages(image = "https://cdn.pixabay.com/photo/2021/12/12/18/04/mountains-6865752_1280.jpg", "Finlandia con hielo")

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePreview() {
    Profile()
}