package dev.valvassori.water.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.valvassori.water.ext.defaultHorizontalPadding

@Composable
fun AuthenticationScreenBody(
    image: Painter,
    title: String,
    subtitle: String? = null,
    body: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {
        BigImage(
            painter = image,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
        )

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
                modifier =
                    Modifier
                        .defaultHorizontalPadding()
                        .padding(top = 32.dp)
                        .align(Alignment.CenterHorizontally),
            )

            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.titleSmall,
                    modifier =
                        Modifier
                            .defaultHorizontalPadding()
                            .align(Alignment.CenterHorizontally),
                )
            }

            body()
        }
    }
}
