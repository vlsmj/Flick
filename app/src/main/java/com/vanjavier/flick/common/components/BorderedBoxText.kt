package com.vanjavier.flick.common.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanjavier.flick.ui.theme.Gray

@Composable
fun BorderedBoxText(text: String) {
    Box(modifier = Modifier
        .border(1.dp, Gray, RoundedCornerShape(4.dp))) {
        Text(text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 8.sp,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
    }
}