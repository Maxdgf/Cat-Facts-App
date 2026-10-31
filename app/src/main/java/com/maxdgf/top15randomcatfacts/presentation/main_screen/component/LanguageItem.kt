package com.maxdgf.top15randomcatfacts.presentation.main_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LanguageItem(
    name: String,
    isSelected: Boolean,
    isElevated: Boolean,
    onClick: () -> Unit
) {
    CardView(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        isElevated = isElevated
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                modifier = Modifier.weight(1.0f)
            )

            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(
                            brush = Brush.radialGradient(colors = listOf(Color(0xFF90FF94), Color(0xFF6DCE6F))),
                            shape = CircleShape
                        )
                )
            }
        }
    }
}