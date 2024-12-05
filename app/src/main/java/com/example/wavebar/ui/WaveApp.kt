package com.example.wavebar.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.asFloatState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wavebar.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaveApp() {
    val wavePosition by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(4000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    ).asFloatState()

    Scaffold(
        bottomBar = {
            WaveBottomBar(
                logo = {
                    Logo(painterResource(R.drawable.ic_launcher_foreground))
                },
                title = { Text("Title") },
                wavePosition = wavePosition,
                wavePeriod = LocalConfiguration.current.screenWidthDp.dp
            )
        }
    ) { innerPadding ->
        Text(
            text = "Hello There!",
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Preview
@Composable
fun WaveBottomBarPreview() {
    WaveBottomBar(
        logo = {
            Logo(painterResource(R.drawable.ic_launcher_foreground))
        },
        title = { Text("Title") }
    )
}
