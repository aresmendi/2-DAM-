package com.ares_caballero.conversorares

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ares_caballero.conversorares.ui.theme.ConversorAresTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConversorAresTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var number by rememberSaveable { mutableDoubleStateOf(0.0) }
    var binary by rememberSaveable { mutableStateOf("") }
    var hexa by rememberSaveable { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.height(dimensionResource(id = R.dimen.topBar)),
                title = {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_200)
                )
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(
                    start = dimensionResource(id = R.dimen.firstColumnHorPadding),
                    top = dimensionResource(id = R.dimen.firstColumnVerPadding)
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.title),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.firstColumnHorPadding)
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.number, number),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.subTitleVerPadding),
                    )
                )
                Button(
                    onClick = { number = Random.nextDouble(0.0, 10000.0) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.purple_200),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.randomButtonStart))
                        .widthIn(min = 100.dp),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.randomText),
                        fontSize = 15.sp
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.binary, binary),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.subTitleVerPadding),
                    )
                )
                Button(
                    onClick = { binary = number.toInt().toString(2) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.teal_700),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.randomButtonStart))
                        .widthIn(min = 100.dp),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.binaryText),
                        fontSize = 15.sp
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.hexa, hexa),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.subTitleVerPadding),
                    )
                )
                Button(
                    onClick = { hexa = number.toInt().toString(16).uppercase() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.teal_700),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.randomButtonStart))
                        .widthIn(min = 100.dp),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.hexaText),
                        fontSize = 15.sp
                    )
                }

            }
            Button(
                onClick = { number = 0.0; binary = ""; hexa = "" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.purple_700),
                    contentColor = colorResource(R.color.white)
                ),
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.resetButtonStart),
                             top = dimensionResource(id = R.dimen.subTitleVerPadding))
                    .widthIn(min = 100.dp),
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.resetText),
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_4
)
@Composable
fun MainScreenPreview() {
    ConversorAresTheme {
        MainScreen()
    }
}