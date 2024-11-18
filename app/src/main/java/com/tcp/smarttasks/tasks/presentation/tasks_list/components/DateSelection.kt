package com.tcp.smarttasks.tasks.presentation.tasks_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.tcp.smarttasks.ui.theme.SmartTasksTheme
import java.time.LocalDate

@Composable
fun DateSelection(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onForwardClick: () -> Unit,
    targetDate: LocalDate
) {
    Row(
        modifier = modifier
            .padding(top = 60.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
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
            text = if (targetDate.isEqual(LocalDate.now())) stringResource(R.string.today)
            else targetDate.toString(),
            textAlign = TextAlign.Center,
            modifier = modifier.weight(1F),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            painter = painterResource(R.drawable.arrow_forward_24),
            contentDescription = stringResource(R.string.arrow_forward),
            modifier = modifier
                .clickable(enabled = true, onClick = onForwardClick)
                .size(30.dp)
                .fillMaxWidth(),
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DateSelectionPreview() {
    SmartTasksTheme {
        DateSelection(onBackClick = {}, onForwardClick = {}, targetDate = LocalDate.now())
    }
}