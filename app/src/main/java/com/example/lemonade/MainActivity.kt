package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> LemonadeStepScreen(step = currentStep) { currentStep = 2 }
            2 -> LemonadeStepScreen(
                step = currentStep,
                maxClicks = Random.nextInt(1, 5)
            ) { currentStep = 3 }

            3 -> LemonadeStepScreen(step = currentStep) { currentStep = 4 }
            4 -> LemonadeStepScreen(step = currentStep) { currentStep = 1 }
        }
    }
}

@Composable
fun LemonadeStepScreen(step: Int, maxClicks: Int = 1, onClick: () -> Unit) {
    var clickAttempts by remember { mutableStateOf(0) }
    val (labelResource, imageResource, imageLabel) = when (step) {
        1 -> Triple(R.string.lemon_tree_label, R.drawable.lemon_tree, R.string.lemon_tree)
        2 -> Triple(R.string.lemon_aqueeze_label, R.drawable.lemon_squeeze, R.string.lemon)
        3 -> Triple(R.string.lemonade_drink_label, R.drawable.lemon_drink, R.string.glass_lemonade)
        else -> Triple(R.string.empty_glass_label, R.drawable.lemon_restart, R.string.empty_glass)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = labelResource),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = imageLabel),
            modifier = Modifier
                .border(
                    BorderStroke(
                        width = 2.dp,
                        color = Color(105, 205, 216),
                    ),
                    RoundedCornerShape(4.dp)
                )
                .clickable {
                    clickAttempts++
                    if (clickAttempts == maxClicks) {
                        onClick()
                    }
                }
        )
    }
}