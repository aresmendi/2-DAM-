package com.ares_caballero.statisticsares

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ares_caballero.statisticsares.ui.theme.StatisticsAresTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StatisticsAresTheme {
                MainScreen()
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4
)
@Composable
fun MainScreenPreview() {
    StatisticsAresTheme {
        MainScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class) // Necesario para usar TopAppBar
@Composable
fun MainScreen() {
    var clicks by rememberSaveable { mutableIntStateOf(0) }
    var people by rememberSaveable { mutableIntStateOf(0) }
    var scooter by rememberSaveable { mutableIntStateOf(0) }
    var bicycle by rememberSaveable { mutableIntStateOf(0) }
    var car by rememberSaveable { mutableIntStateOf(0) }
    var peoplePercent by rememberSaveable { mutableIntStateOf(0) }
    var scootersPercent by rememberSaveable { mutableIntStateOf(0) }
    var byciclesPercent by rememberSaveable { mutableIntStateOf(0) }
    var carsPercent by rememberSaveable { mutableIntStateOf(0) }
    peoplePercent = if (people != 0) (people * 100) / clicks
    else 0
    scootersPercent = if (scooter != 0) (scooter * 100) / clicks
    else 0
    byciclesPercent = if (bicycle != 0) (bicycle * 100) / clicks
    else 0
    carsPercent = if (car != 0) (car * 100) / clicks
    else 0
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
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(
                    start = dimensionResource(id = R.dimen.firstColumnHorPadding),
                    top = dimensionResource(id = R.dimen.firstColumnVerPadding),
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
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.subtitle, clicks),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(
                        top = dimensionResource(id= R.dimen.subTitleVerPadding),
                        start = dimensionResource(id = R.dimen.subTitleHorPadding)
                    )
                )

                Button(
                    onClick = { clicks = 0; people = 0; scooter = 0; bicycle = 0; car = 0 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.resetButton),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.resetButtonStart))
                        .widthIn(min = 100.dp),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.reset),
                        fontSize = 15.sp
                    )
                }
            }
            Text(
                stringResource(R.string.peopleTitle),
                Modifier.padding(
                    start = dimensionResource(R.dimen.buttonsTittle),
                    top = dimensionResource(R.dimen.subTitleVerPadding)
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(R.dimen.subTitleVerPadding)
                    ),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = stringResource(R.string.people, people),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                )
                Button(
                    onClick = { clicks++; people++ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.green),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.add)
                    )
                }
                Button(
                    onClick = { clicks--; people-- },
                    enabled = people >0,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.yellow),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.minus)
                    )
                }
                Button(
                    onClick = { clicks-=people;people = 0 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.resetButton),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.resetSimple)
                    )
                }
            }
            Text(
                stringResource(R.string.scooterTitle),
                Modifier.padding(
                    start = dimensionResource(R.dimen.buttonsTittle),
                    top = dimensionResource(R.dimen.subTitleVerPadding)
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(R.dimen.subTitleVerPadding)
                    ),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = stringResource(R.string.scooter, scooter),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                )
                Button(
                    onClick = { clicks++; scooter++ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.green),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.add)
                    )
                }
                Button(
                    onClick = { clicks--;scooter-- },
                    enabled = scooter >0,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.yellow),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.minus)
                    )
                }
                Button(
                    onClick = { clicks-=scooter;scooter = 0 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.resetButton),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.resetSimple)
                    )
                }
            }
            Text(
                stringResource(R.string.bicycleTitle),
                Modifier.padding(
                    start = dimensionResource(R.dimen.buttonsTittle),
                    top = dimensionResource(R.dimen.subTitleVerPadding)
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(R.dimen.subTitleVerPadding)
                    ),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = stringResource(R.string.bycicle, bicycle),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                )
                Button(
                    onClick = { clicks++;bicycle++ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.green),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.add)
                    )
                }
                Button(
                    onClick = { clicks--;bicycle-- },
                    enabled = bicycle > 0,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.yellow),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.minus)
                    )
                }
                Button(
                    onClick = { clicks-=bicycle;bicycle = 0 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.resetButton),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.resetSimple)
                    )
                }
            }
            Text(
                stringResource(R.string.carTitle),
                Modifier.padding(
                    start = dimensionResource(R.dimen.buttonsTittle),
                    top = dimensionResource(R.dimen.subTitleVerPadding)
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(R.dimen.subTitleVerPadding)
                    ),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = stringResource(R.string.car, car),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                )
                Button(
                    onClick = { clicks++;car++ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.green),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.add)
                    )
                }
                Button(
                    onClick = { clicks--;car-- },
                    enabled = car > 0,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.yellow),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.minus)
                    )
                }
                Button(
                    onClick = { clicks-=car;car = 0 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.resetButton),
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.buttonsControl)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.resetSimple)
                    )
                }
            }
            Text(
                stringResource(R.string.stadisticsTitle),
                Modifier.padding(
                    start = dimensionResource(R.dimen.buttonsTittle),
                    top = dimensionResource(R.dimen.subTitleVerPadding)
                )
            )
            Text(
                text = stringResource(R.string.peopleStadistics, peoplePercent),
                Modifier.padding(
                    start = dimensionResource(R.dimen.buttonsTittle),
                    top = dimensionResource(R.dimen.subTitleVerPadding)
                )
            )
            Text(
                text = stringResource(R.string.scootersStadistics, scootersPercent),
                Modifier.padding(
                    start = dimensionResource(R.dimen.buttonsTittle),
                )
            )
            Text(
                text = stringResource(R.string.byciclesStadistics, byciclesPercent),
                Modifier.padding(
                    start = dimensionResource(R.dimen.buttonsTittle),
                )
            )
            Text(
                text = stringResource(R.string.carsStadistics, carsPercent),
                Modifier.padding(
                    start = dimensionResource(R.dimen.buttonsTittle),
                )
            )
        }
    }
}
