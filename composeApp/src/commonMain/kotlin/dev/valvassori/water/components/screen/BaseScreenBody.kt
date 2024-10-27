package dev.valvassori.water.components.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.valvassori.water.ext.defaultHorizontalPadding

@Composable
fun BaseScreenBody(
    title: String,
    subtitle: String? = null,
    image: Painter? = null,
    modifier: Modifier = Modifier,
    body: @Composable ColumnScope.() -> Unit,
) {
    val bodyModifier =
        Modifier
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .then(modifier),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (image != null) {
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier =
                        Modifier
                            .fillMaxWidth(0.5f)
                            .padding(top = 32.dp),
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    TitleAndSubtitle(title, subtitle, onTopBar = true)
                }
            }
        }

        if (image != null) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .then(bodyModifier),
            ) {
                TitleAndSubtitle(title, subtitle, onTopBar = false)

                body()
            }
        } else {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .then(bodyModifier),
            ) {
                body()
            }
        }
    }
}

@Composable
private fun ColumnScope.TitleAndSubtitle(
    title: String,
    subtitle: String?,
    onTopBar: Boolean,
) {
    val color =
        if (onTopBar) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onSurface
        }

    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        style = if (onTopBar) MaterialTheme.typography.titleMedium else MaterialTheme.typography.titleLarge,
        color = color,
        modifier =
            Modifier
                .defaultHorizontalPadding()
                .padding(
                    top = if (onTopBar) 16.dp else 32.dp,
                    bottom = if (onTopBar && subtitle == null) 16.dp else 0.dp,
                ).align(Alignment.CenterHorizontally),
    )

    if (subtitle != null) {
        Text(
            text = subtitle,
            style = if (onTopBar) MaterialTheme.typography.bodySmall else MaterialTheme.typography.titleSmall,
            color = color,
            modifier =
                Modifier
                    .defaultHorizontalPadding()
                    .padding(bottom = if (onTopBar) 16.dp else 0.dp)
                    .align(Alignment.CenterHorizontally),
        )
    }
}
