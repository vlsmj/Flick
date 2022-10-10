package com.vanjavier.flick.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanjavier.flick.R
import com.vanjavier.flick.ui.theme.Black

/**
 * Composable with logo or text and can redirect to Search.
 *
 * @param logo will be shown if provided.
 * @param title will be shown if provided.
 * @param onNavigateToSearch listener that navigates to Search when clicked.
 */
@Composable
fun TopBar(
    logo: Painter? = null,
    title: String? = "",
    onNavigateToSearch: () -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(brush = Brush.verticalGradient(
                colors = listOf(
                    Black,
                    Color.Transparent
                ),
                startY = 10f,
                endY = 140f
            ))
            .padding(8.dp)) {

        Box(contentAlignment = Alignment.Center) {
            logo?.let {
                Image(painter = it, contentDescription = "logo",
                    modifier = Modifier
                        .size(60.dp))
            }

            title?.let {
                Text(text = it,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)
            }
        }

        Icon(painter = painterResource(id = R.drawable.ic_baseline_search_24),
            contentDescription = "search",
            tint = Color.White,
            modifier = Modifier.clickable {
                onNavigateToSearch()
            })
    }
}