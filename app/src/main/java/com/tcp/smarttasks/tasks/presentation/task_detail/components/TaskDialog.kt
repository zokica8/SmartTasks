package com.tcp.smarttasks.tasks.presentation.task_detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.tcp.smarttasks.R
import com.tcp.smarttasks.ui.theme.LightRed
import com.tcp.smarttasks.ui.theme.SmartTasksTheme

@Composable
fun TaskDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onClickingYes: () -> Unit
) {
    Dialog(
        onDismissRequest = {},
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = stringResource(R.string.add_comment),
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(top = 30.dp, start = 16.dp, end = 16.dp)
            )
            Spacer(modifier = modifier.weight(1F))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(R.string.no),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightRed,
                    modifier = modifier
                        .padding(10.dp)
                        .clickable(enabled = true, onClick = onDismissRequest)
                )

                Text(
                    text = stringResource(R.string.yes),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = modifier
                        .padding(10.dp)
                        .clickable(enabled = true, onClick = onClickingYes)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDialogPreview() {
    SmartTasksTheme {
        TaskDialog(onDismissRequest = {}, onClickingYes = {})
    }
}