package com.tcp.smarttasks.tasks.presentation.task_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tcp.smarttasks.R
import com.tcp.smarttasks.ui.theme.AmsiPro
import com.tcp.smarttasks.ui.theme.LightGreen

@Composable
fun CommentTextArea(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val text = rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = text.value,
            onValueChange = { text.value = it },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black
            ),
            modifier = modifier
                .background(Color.White)
                .fillMaxWidth()
                .height(300.dp)
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
        )
        Spacer(modifier = modifier.size(30.dp))
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .padding(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGreen
            )
        ) {
            Text(text = stringResource(R.string.send),
                fontSize = 24.sp,
                fontFamily = AmsiPro,
                fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommentTextAreaPreview() {
    CommentTextArea(onClick = {})
}