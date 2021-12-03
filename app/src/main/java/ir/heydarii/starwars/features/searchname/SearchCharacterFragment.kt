package ir.heydarii.starwars.features.searchname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseFragment
import ir.heydarii.starwars.features.searchname.compose.SearchCharacterCompose
import ir.heydarii.starwars.pojo.CharacterSearchResult
import ir.heydarii.starwars.pojo.SearchCharacterResource
import ir.heydarii.starwars.ui.theme.StarWarsTheme

/**
 * User can search any StarWars character name here
 */
@AndroidEntryPoint
class SearchCharacterFragment : BaseFragment() {

    private val viewModel by viewModels<SearchCharacterViewModel>()
    private val characters = mutableStateOf(arrayListOf<CharacterSearchResult>())
    private var isLoading by mutableStateOf(false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_character_search, container, false).apply {
        findViewById<ComposeView>(R.id.compose).setContent {
            StarWarsTheme {
                var character by remember { mutableStateOf("") }
                SearchCharacterCompose(
                    character = character,
                    characters = characters.value,
                    onCharacterChanged = {
                        character = it
                        viewModel.searchCharacterName(it)
                    },
                    onCharacterClicked = {
                        findNavController().navigate(
                            SearchCharacterFragmentDirections.showCharacterDetailsAction(it)
                        )
                    },
                    isLoading = isLoading
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchNameData.observe(viewLifecycleOwner) {
            when (it) {
                is SearchCharacterResource.Loading -> isLoading = true
                is SearchCharacterResource.Error -> {
                    isLoading = false
                    showError(requireView(), getString(R.string.some_errors_while_fetching_data)) {}
                }
                is SearchCharacterResource.Success -> {
                    isLoading = false
                    characters.value.clear()
                    characters.value.addAll(it.data?.results!!)
                }
            }
        }
    }
}
