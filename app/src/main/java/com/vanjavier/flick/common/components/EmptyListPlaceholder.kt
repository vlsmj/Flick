package com.vanjavier.flick.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanjavier.flick.R
import com.vanjavier.flick.ui.theme.DarkerGray

/**
 * Empty composable placeholder for empty lists.
 */
@Composable
fun EmptyListPlaceholder() {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.ic_popcorn), contentDescription = "empty",
            modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(id = R.string.empty_list_body),
            color = DarkerGray,
            fontSize = 12.sp)
    }
}