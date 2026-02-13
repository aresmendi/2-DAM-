package com.ares_caballero.contadordeclics.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ares_caballero.contadordeclics.R
import com.ares_caballero.contadordeclics.ui.theme.MyAppContent

@Composable
fun ClickCounter() {
    var clicks by rememberSaveable{ mutableIntStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.contador, clicks),
            fontSize = dimensionResource(id = R.dimen.counter_text).value.sp
        )
        Button(onClick = {
            clicks++
        }) {
            Text(
                text = stringResource(id = R.string.button_text),
                fontSize = dimensionResource(id = R.dimen.button_text).value.sp,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.button_padding))
            )
        }
    }
}

@Preview(
    locale = "es",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ClickCounterPreview() {
    MyAppContent {
        ClickCounter()
    }
}