package ir.heydarii.starwars.features.searchname.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.heydarii.starwars.R
import ir.heydarii.starwars.pojo.CharacterSearchResult
import ir.heydarii.starwars.ui.theme.itemBackground
import ir.heydarii.starwars.ui.theme.searchStrokeColor

@Composable
fun SearchCharacterCompose(
    character: String,
    characters: List<CharacterSearchResult>,
    onCharacterChanged: (String) -> Unit,
    onCharacterClicked: (String) -> Unit,
    isLoading: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.size(60.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = character,
            onValueChange = { onCharacterChanged(it) },
            keyboardActions = KeyboardActions(onSearch = { onCharacterChanged(character) }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            ),
            trailingIcon = { Trailing(character, onCharacterChanged) },
            leadingIcon = {
                if (isLoading)
                    LeadingProgressbar()
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.enter_a_character_name),
                    color = Color.Gray,
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .padding(16.dp, 0.dp)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.searchStrokeColor,
                    MaterialTheme.shapes.medium
                )
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(8.dp))
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(characters) {
                CharacterItem(name = it.name, url = it.url, onCharacterClicked = onCharacterClicked)
            }
        }
    }
}

@Composable
fun LeadingProgressbar() {
    CircularProgressIndicator(
        modifier = Modifier.size(32.dp),
        color = MaterialTheme.colorScheme.searchStrokeColor,
        strokeWidth = 1.dp
    )
}

@Composable
fun Trailing(character: String, onCharacterChanged: (String) -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = stringResource(id = R.string.search),
        modifier = Modifier
            .size(48.dp)
            .clickable { onCharacterChanged(character) }
            .padding(12.dp)
    )
}

@Composable
fun CharacterItem(name: String, url: String, onCharacterClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onCharacterClicked(url) }
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.itemBackground, MaterialTheme.shapes.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            modifier = Modifier.padding(0.dp, 16.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Preview
@Composable
fun SearchPagePreview() {
    SearchCharacterCompose("", listOf(), {}, {}, false)
}

@Preview
@Composable
fun CharacterItemPreview() {
    CharacterItem("Pouya", "", {})
}