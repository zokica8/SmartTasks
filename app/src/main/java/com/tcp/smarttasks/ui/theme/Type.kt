package com.tcp.smarttasks.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tcp.smarttasks.R

// Set of Material typography styles to start with
val AmsiPro = FontFamily(
    Font(
        resId = R.font.amsipro_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.amsipro_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.amsipro_bold,
        weight = FontWeight.ExtraBold
    )
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = AmsiPro,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = AmsiPro,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 0.3.sp
    )
)