package com.tcp.smarttasks.tasks.presentation.tasks_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tcp.smarttasks.R
import com.tcp.smarttasks.ui.theme.SmartTasksTheme

@Composable
fun NoTasksScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.size(200.dp))
        Image(
            painter = painterResource(R.drawable.empty_screen),
            modifier = modifier
                .size(300.dp)
                .weight(1F),
            contentDescription = stringResource(R.string.no_tasks)
        )
        Spacer(modifier.size(30.dp))
        Text(
            text = stringResource(R.string.no_tasks),
            modifier = modifier
                .padding(40.dp)
                .weight(1F),
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoTasksScreenPreview() {
    SmartTasksTheme {
        NoTasksScreen()
    }
}