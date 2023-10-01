package ir.heydarii.starwars.features.searchname.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.heydarii.starwars.R
import ir.heydarii.starwars.pojo.CharacterSearchResult

@Composable
fun SearchCharacterScreen(
    character: String,
    onCharacterChanged: (String) -> Unit,
    isLoading: Boolean = false,
    characters: List<CharacterSearchResult>,
    onCharacterClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.size(60.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        SearchCharacterTextFieldComponent(
            character = character,
            onCharacterChanged = onCharacterChanged,
            isLoading = isLoading
        )
        Spacer(modifier = Modifier.size(8.dp))
        LazyColumn(
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(characters) {
                CharacterItemComponent(
                    title = it.name,
                    url = it.url,
                    onItemClicked = onCharacterClicked
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SearchCharacterScreen("", {}, false, listOf(), {})
}