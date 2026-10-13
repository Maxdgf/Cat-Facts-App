package com.maxdgf.top15randomcatfacts.presentation.main_screen.component

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun CardView(
    modifier: Modifier,
    isElevated: Boolean,
    content: @Composable () -> Unit
) {
    if (isElevated) {
        ElevatedCard(
            modifier = modifier,
            elevation = CardDefaults.elevatedCardElevation(0.dp)
        ) { content() }
    } else
        Card(modifier = modifier) { content() }
}