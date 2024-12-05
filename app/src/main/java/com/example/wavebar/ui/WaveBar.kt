package com.example.wavebar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Default values provided for [WaveBottomBar].
 * */
object WaveBarDefaults {
    val WaveAmplitude = 20.dp
    val WavePeriod = 240.dp
}

/**
 * Context for [Logo] provided by [LocalLogoContext].
 * */
data class LogoContext(
    val size: DpSize = DpSize(96.dp, 96.dp),
    val padding: PaddingValues = PaddingValues(8.dp)
)

/**
 * The CompositionLocal to provide the size and padding for [Logo].
 * */
val LocalLogoContext = compositionLocalOf { LogoContext() }

/**
 * Bottom bar with [WaveShape] and customizable amplitude, period and position of wave.
 * */
@Composable
fun WaveBottomBar(
    logo: @Composable () -> Unit = { },
    title: @Composable () -> Unit = { },
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    waveAmplitude: Dp = WaveBarDefaults.WaveAmplitude,
    wavePeriod: Dp = WaveBarDefaults.WavePeriod,
    wavePosition: Float = 0f
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = WaveShape(
            period = wavePeriod,
            amplitude = waveAmplitude,
            position = wavePosition
        ),
        color = color
    ) {
        Row(
            modifier = Modifier
                .padding(
                    top = waveAmplitude * 2,
                    start = 24.dp,
                    bottom = with(LocalDensity.current) {
                        WindowInsets.systemBars
                            .getBottom(Density(density))
                            .toDp()
                    }
                )
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            val logoContext = LogoContext(
                size = DpSize(48.dp, 48.dp),
                padding = PaddingValues(4.dp)
            )

            CompositionLocalProvider(LocalLogoContext provides logoContext) { logo() }

            ProvideTextStyle(value = MaterialTheme.typography.titleLarge) { title() }
        }
    }
}


/**
 * Logo for [WaveBottomBar] in general.
 * */
@Composable
fun Logo(painter: Painter, modifier: Modifier = Modifier) {
    Icon(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .padding(LocalLogoContext.current.padding)
            .size(LocalLogoContext.current.size)
    )
}