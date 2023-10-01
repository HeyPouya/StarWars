package ir.heydarii.starwars.features.searchname.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.heydarii.starwars.R
import ir.heydarii.starwars.ui.theme.StarWarsTheme

@Composable
fun SearchCharacterTextFieldComponent(
    modifier: Modifier = Modifier,
    character: String = "",
    onCharacterChanged: (String) -> Unit = {},
    isLoading: Boolean = false,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        value = character,
        shape = MaterialTheme.shapes.medium,
        onValueChange = { onCharacterChanged(it) },
        keyboardActions = KeyboardActions(onSearch = { onCharacterChanged(character) }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        trailingIcon = { TrailingIcon(character, onCharacterChanged) },
        leadingIcon = { LeadingProgressbar(isLoading) },
        placeholder = { PlaceHolderText() },
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = MaterialTheme.colorScheme.outline,
        ),
    )
}

@Composable
private fun PlaceHolderText() {
    Text(
        text = stringResource(id = R.string.enter_a_character_name),
        color = Color.Gray,
    )
}

@Composable
private fun LeadingProgressbar(isLoading: Boolean) {
    if (isLoading)
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = MaterialTheme.colorScheme.onPrimary,
            strokeWidth = 1.dp
        )
}

@Composable
private fun TrailingIcon(character: String, onCharacterChanged: (String) -> Unit) {
    Image(
        painter = painterResource(id = androidx.appcompat.R.drawable.abc_ic_search_api_material),
        contentDescription = stringResource(id = R.string.search),
        modifier = Modifier
            .size(48.dp)
            .clickable { onCharacterChanged(character) }
            .padding(12.dp)
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0x000)
private fun Preview() {
    StarWarsTheme {
        SearchCharacterTextFieldComponent()
    }
}
