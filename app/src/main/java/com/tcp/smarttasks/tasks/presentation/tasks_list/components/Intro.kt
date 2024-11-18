package com.tcp.smarttasks.tasks.presentation.tasks_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tcp.smarttasks.R
import com.tcp.smarttasks.ui.theme.DarkYellow
import com.tcp.smarttasks.ui.theme.LightYellow
import com.tcp.smarttasks.ui.theme.SmartTasksTheme

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .background(
                color = if (isSystemInDarkTheme())
                    DarkYellow else LightYellow
            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 150.dp)
                .size(250.dp),
            contentDescription = stringResource(R.string.logo)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.intro_illustration),
            modifier = modifier
                .fillMaxWidth()
                .size(250.dp),
            contentDescription = stringResource(R.string.intro_illustration)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    SmartTasksTheme {
        IntroScreen()
    }
}