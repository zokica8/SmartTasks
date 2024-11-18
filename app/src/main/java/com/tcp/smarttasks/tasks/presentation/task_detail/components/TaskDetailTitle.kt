package com.tcp.smarttasks.tasks.presentation.task_detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tcp.smarttasks.R
import com.tcp.smarttasks.ui.theme.AmsiPro

@Composable
fun TaskDetailTitle(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 60.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.arrow_back_24),
            contentDescription = stringResource(R.string.arrow_back),
            modifier = modifier
                .clickable(enabled = true, onClick = onBackClick)
                .size(30.dp)
                .fillMaxWidth(),
            tint = Color.White
        )
        Text(
            text = stringResource(R.string.task_detail),
            textAlign = TextAlign.Center,
            modifier = modifier.weight(1F),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = AmsiPro,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailTitlePreview() {
    TaskDetailTitle(
        onBackClick = {}
    )
}