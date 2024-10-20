package dev.valvassori.water.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ButtonWithDesc(
    desc: String,
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = desc,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
        TextButton(
            onClick = onClick,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.align(Alignment.CenterVertically),
        ) {
            Text(
                text = buttonText,
                fontSize = 14.sp,
            )
        }
    }
}
