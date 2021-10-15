package ir.heydarii.starwars.features.searchname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.Navigation
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseFragment
import ir.heydarii.starwars.base.ViewModelFactory
import ir.heydarii.starwars.features.searchname.adapter.SearchCharacterDiffUtilsCallback
import ir.heydarii.starwars.features.searchname.adapter.SearchNameRecyclerAdapter
import ir.heydarii.starwars.pojo.CharacterSearchResult
import kotlinx.android.synthetic.main.fragment_character_search.*
import javax.inject.Inject

/**
 * User can search any StarWars character name here
 */
class SearchCharacterFragment : BaseFragment() {

    private lateinit var viewModel: SearchCharacterViewModel
    private lateinit var adapter: SearchNameRecyclerAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    /**
     * inflating the view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    ShowContent()
                }
            }
        }
    }

    @Preview
    @Composable
    fun ShowContent() {
        var value by remember { mutableStateOf("") }
        Column(Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.padding(top = 64.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(top = 32.dp))
            TextField(
                value = value,
                onValueChange = {value = it},
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )
        }
    }

    /**
     * All codes are here
     */
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // instantiating the viewModel
//        viewModel =
//            ViewModelProvider(this, viewModelFactory).get(SearchCharacterViewModel::class.java)
//
//        viewModel.searchResultData().observe(this, Observer {
//
//            when (it) {
//                is SearchCharacterResource.Loading -> {
//                    loading.visibility = View.VISIBLE
//                }
//                is SearchCharacterResource.Error -> {
//                    loading.visibility = View.INVISIBLE
//                    showError(rootView, getString(R.string.some_errors_while_fetching_data)) {
//                        searchCharacter(edtSearchName.text.toString())
//                    }
//                }
//                is SearchCharacterResource.Success -> {
//                    loading.visibility = View.INVISIBLE
//                    showResultsInRecycler(it.data?.results?.toMutableList())
//                }
//            }
//        })
//
//        // instantiating the Recycler
//        makeRecyclerAdapter()
//
//        // starting the search by clicking on the image
//        imgSearch.setOnClickListener {
//            searchCharacter(edtSearchName.text.toString())
//        }
//
//        // starting the search by keyboard
//        edtSearchName.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                searchCharacter(edtSearchName.text.toString())
//            }
//            true
//        }
//    }

    private fun searchCharacter(characterName: String) {
        viewModel.searchCharacterName(characterName)
    }

    private fun makeRecyclerAdapter() {
        adapter = SearchNameRecyclerAdapter(SearchCharacterDiffUtilsCallback()) { url: String ->
            onCharacterSelected(url)
        }
        recycler.adapter = adapter
    }

    private fun onCharacterSelected(url: String) {
        val action = SearchCharacterFragmentDirections.showCharacterDetailsAction(url)
        Navigation.findNavController(rootView).navigate(action)
    }

    private fun showResultsInRecycler(searchData: List<CharacterSearchResult>?) {
        adapter.submitList(searchData)
    }

    /**
     * Making sure that memory leak doesn't happen
     */
    override fun onDestroyView() {
        super.onDestroyView()
        recycler.adapter = null
    }
}
