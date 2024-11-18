package com.tcp.smarttasks.tasks.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tcp.smarttasks.R
import com.tcp.smarttasks.ui.theme.AmsiPro
import com.tcp.smarttasks.ui.theme.DarkYellow
import com.tcp.smarttasks.ui.theme.LightRed
import com.tcp.smarttasks.ui.theme.LightYellow

@Composable
fun NoInternetScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .background(if (isSystemInDarkTheme()) DarkYellow else LightYellow),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.no_internet),
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontFamily = AmsiPro,
            fontWeight = FontWeight.Bold,
            color = LightRed,
            lineHeight = 80.sp,
            modifier = modifier.padding(5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoInternetScreenPreview() {
    NoInternetScreen()
}