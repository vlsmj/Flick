package com.vanjavier.flick.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.sp

/**
 * Composable for an icon above and text beneath it.
 *
 * @param modifier style and properties of the composable.
 * @param icon the icon provided.
 * @param text the text provided.
 * @param onClick click listener for the icons with text.
 */
@Composable
fun IconText(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    onClick: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable {
            onClick()
        }) {
        Icon(painter = icon, contentDescription = text,
            tint = Color.White)
        Text(text = text,
            fontSize = 10.sp)
    }
}