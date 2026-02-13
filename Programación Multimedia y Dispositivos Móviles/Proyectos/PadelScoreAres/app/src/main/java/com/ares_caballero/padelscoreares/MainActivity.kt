package com.ares_caballero.padelscoreares

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.ares_caballero.padelscoreares.ui.theme.PadelScoreAresTheme
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PadelScoreAresTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var setScore1 by rememberSaveable { mutableIntStateOf(0) }
    var setScore2 by rememberSaveable { mutableIntStateOf(0) }

    var gameScore1 by rememberSaveable { mutableIntStateOf(0) }
    var gameScore2 by rememberSaveable { mutableIntStateOf(0) }

    var actualSetScore1 by rememberSaveable { mutableIntStateOf(0) }
    var actualSetScore2 by rememberSaveable { mutableIntStateOf(0) }

    var isTieBreak by rememberSaveable { mutableStateOf(false) }

    fun pointsToDisplay(points: Int, isTieBreak: Boolean): Int {
        return if (isTieBreak) {
            points
        } else {
            when (points) {
                0 -> 0
                1 -> 15
                2 -> 30
                3 -> 40
                else -> 40
            }
        }
    }

    fun actualGameWinned(actualSetScore1: Int, actualSetScore2: Int): Boolean {
        if (!isTieBreak) {
            if (actualSetScore1 >= 4 && actualSetScore1 - actualSetScore2 >= 2) {
                gameScore1++
                return true
            }
            if (actualSetScore2 >= 4 && actualSetScore2 - actualSetScore1 >= 2) {
                gameScore2++
                return true
            }
        } else {
            if (actualSetScore1 >= 7 && actualSetScore1 - actualSetScore2 >= 2) {
                setScore1++
                isTieBreak = false
                gameScore1 = 0
                gameScore2 = 0
                return true
            }
            if (actualSetScore2 >= 7 && actualSetScore2 - actualSetScore1 >= 2) {
                setScore2++
                isTieBreak = false
                gameScore1 = 0
                gameScore2 = 0
                return true
            }
        }
        return false
    }

    fun actualSetWinned(gameScore1: Int, gameScore2: Int): Boolean {
        if (gameScore1 == 6 && gameScore2 == 6) {
            isTieBreak = true
            return false
        }
        if (gameScore1 >= 6 && gameScore1 - gameScore2 >= 2) {
            setScore1++
            return true
        }
        if (gameScore2 >= 6 && gameScore2 - gameScore1 >= 2) {
            setScore2++
            return true
        }
        return false
    }

    fun winMatch(setScore1: Int, setScore2: Int): Boolean {
        if (setScore1 >= 2 ) return true
        if (setScore2 >= 2 ) return true
        return false
    }

    fun resetAll() {
        setScore1 = 0
        setScore2 = 0
        gameScore1 = 0
        gameScore2 = 0
        actualSetScore1 = 0
        actualSetScore2 = 0
        isTieBreak = false
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.height(dimensionResource(id = R.dimen.topBar)),
                title = {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.red)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.orange)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.setString),
                    fontSize = dimensionResource(id = R.dimen.titleTextPadding).value.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.orange)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = colorResource(id = R.color.white))
                        .padding(top = dimensionResource(id = R.dimen.firstColumnVerPadding)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.setScore1, setScore1),
                        fontSize = dimensionResource(id = R.dimen.scoreTextSize).value.sp,
                        color = colorResource(id = R.color.red)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = colorResource(id = R.color.white))
                        .padding(top = dimensionResource(id = R.dimen.firstColumnVerPadding)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.setScore2, setScore2),
                        color = colorResource(id = R.color.purple_700),
                        fontSize = dimensionResource(id = R.dimen.scoreTextSize).value.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.orange)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.gameString),
                    fontSize = dimensionResource(id = R.dimen.titleTextPadding).value.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.orange)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = colorResource(id = R.color.white))
                        .padding(top = dimensionResource(id = R.dimen.firstColumnVerPadding)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.gameScore1, gameScore1),
                        fontSize = dimensionResource(id = R.dimen.scoreTextSize).value.sp,
                        color = colorResource(id = R.color.red)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = colorResource(id = R.color.white))
                        .padding(top = dimensionResource(id = R.dimen.firstColumnVerPadding)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.gameScore2, gameScore2),
                        color = colorResource(id = R.color.purple_700),
                        fontSize = dimensionResource(id = R.dimen.scoreTextSize).value.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.orange)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.actualSetString),
                    fontSize = dimensionResource(id = R.dimen.titleTextPadding).value.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.orange)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = colorResource(id = R.color.white))
                        .padding(top = dimensionResource(id = R.dimen.firstColumnVerPadding)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.actualSetScore1,
                            pointsToDisplay(actualSetScore1, isTieBreak)
                        ),
                        fontSize = dimensionResource(id = R.dimen.scoreTextSize).value.sp,
                        color = colorResource(id = R.color.red)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = colorResource(id = R.color.white))
                        .padding(top = dimensionResource(id = R.dimen.firstColumnVerPadding)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.actualSetScore2,
                            pointsToDisplay(actualSetScore2, isTieBreak)
                        ),
                        color = colorResource(id = R.color.purple_700),
                        fontSize = dimensionResource(id = R.dimen.scoreTextSize).value.sp
                    )
                }
            }
            if (winMatch(setScore1, setScore2)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.firstColumnVerPadding))
                        .background(color = if (setScore1 > setScore2) colorResource(id = R.color.red)
                        else colorResource(id = R.color.purple_500)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = if (setScore1 > setScore2)
                            stringResource(id = R.string.team1Won)
                        else
                            stringResource(id = R.string.team2Won),
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.white)
                    )
                    Button(
                        onClick = { resetAll() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.orange),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.resetButton),
                            color = colorResource(id = R.color.white)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = colorResource(id = R.color.white))
                        .padding(top = dimensionResource(id = R.dimen.firstColumnVerPadding)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(
                        onClick = {
                            actualSetScore1++
                            if (actualGameWinned(actualSetScore1, actualSetScore2)) {
                                actualSetScore1 = 0
                                actualSetScore2 = 0
                            }
                            if (actualSetWinned(gameScore1, gameScore2)) {
                                gameScore1 = 0
                                gameScore2 = 0
                                isTieBreak = false
                            }

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.red),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        enabled = !winMatch(setScore1, setScore2)
                    ) {
                        Text(
                            text = stringResource(id = R.string.button1),
                            color = colorResource(id = R.color.white)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = colorResource(id = R.color.white))
                        .padding(top = dimensionResource(id = R.dimen.firstColumnVerPadding)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(
                        onClick = {
                            actualSetScore2++
                            if (actualGameWinned(actualSetScore1, actualSetScore2)) {
                                actualSetScore1 = 0
                                actualSetScore2 = 0
                            }
                            if (actualSetWinned(gameScore1, gameScore2)) {
                                gameScore1 = 0
                                gameScore2 = 0
                                isTieBreak = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.purple_500),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        enabled = !winMatch(setScore1, setScore2)
                    ) {
                        Text(
                            text = stringResource(id = R.string.button2),
                            color = colorResource(id = R.color.white)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = { resetAll() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.orange),
                        contentColor = colorResource(id = R.color.white)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.resetButton),
                        color = colorResource(id = R.color.white)
                    )
                }
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
    PadelScoreAresTheme {
        MainScreen()
    }
}