package ir.heydarii.starwars.features.searchname.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CharacterItemComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    url: String = "",
    onItemClicked: (String) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClicked(url) }
            .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            modifier = Modifier.padding(0.dp, 16.dp)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CharacterItemComponent(
        title = "Luke Skywalker"
    )
}