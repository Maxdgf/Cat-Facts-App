package com.maxdgf.top15randomcatfacts.presentation.main_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxdgf.top15randomcatfacts.R

@Composable
fun FactItem(
    number: Int,
    fact: String,
    isElevated: Boolean,
    onClick: () -> Unit,
    onShare: () -> Unit
) {
    CardView(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        isElevated = isElevated
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = number.toString(),
                fontWeight = FontWeight.Light,
                fontSize = 15.sp
            )

            Text(
                text = "\t" + fact,
                modifier = Modifier.weight(1.0f)
            )

            IconButton(onClick = onShare) {
                Icon(
                    painter = painterResource(R.drawable.baseline_share_24),
                    contentDescription = "share fact button"
                )
            }
        }
    }
}