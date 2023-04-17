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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
    var squeezeCount by remember { mutableStateOf(0) }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> LemonTextAndImage(
                labelResourceId = R.string.lemon_tree_label,
                imageResourceId = R.drawable.lemon_tree,
                imageContentResourceId = R.string.lemon_tree,
                onImageClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                }
            )
            2 -> LemonTextAndImage(
                labelResourceId = R.string.lemon_aqueeze_label,
                imageResourceId = R.drawable.lemon_squeeze,
                imageContentResourceId = R.string.lemon,
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                }
            )
            3 -> LemonTextAndImage(
                labelResourceId = R.string.lemonade_drink_label,
                imageResourceId = R.drawable.lemon_drink,
                imageContentResourceId = R.string.glass_lemonade,
                onImageClick = { currentStep = 4 }
            )
            4 -> LemonTextAndImage(
                labelResourceId = R.string.empty_glass_label,
                imageResourceId = R.drawable.lemon_restart,
                imageContentResourceId = R.string.empty_glass,
                onImageClick = { currentStep = 1 }
            )
        }
    }
}

@Composable
fun LemonTextAndImage(
    labelResourceId: Int,
    imageResourceId: Int,
    imageContentResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = labelResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = stringResource(id = imageContentResourceId),
            modifier = Modifier
                .wrapContentSize()
                .border(
                    BorderStroke(
                        width = 2.dp,
                        color = Color(105, 205, 216),
                    ),
                    RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
                .clickable(onClick = onImageClick)
        )
    }
}