package ir.heydarii.starwars.features.searchname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseFragment
import ir.heydarii.starwars.features.searchname.adapter.SearchCharacterDiffUtilsCallback
import ir.heydarii.starwars.features.searchname.adapter.SearchNameRecyclerAdapter
import ir.heydarii.starwars.features.searchname.response.SearchCharacterResource
import ir.heydarii.starwars.pojo.CharacterSearchResult
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_character_search.*

/**
 * User can search any StarWars character name here
 */
@AndroidEntryPoint
class SearchCharacterFragment : BaseFragment() {

    private val viewModel by viewModels<SearchCharacterViewModel>()
    private lateinit var adapter: SearchNameRecyclerAdapter

    /**
     * inflating the view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_search, container, false)
    }

    /**
     * All codes are here
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.searchResultData().observe(viewLifecycleOwner) {

            when (it) {
                is SearchCharacterResource.Loading -> {
                    loading.visibility = View.VISIBLE
                }
                is SearchCharacterResource.Error -> {
                    loading.visibility = View.INVISIBLE
                    showError(rootView, getString(R.string.some_errors_while_fetching_data)) {
                        searchCharacter(edtSearchName.text.toString())
                    }
                }
                is SearchCharacterResource.Success -> {
                    loading.visibility = View.INVISIBLE
                    showResultsInRecycler(it.data?.results?.toMutableList())
                }
            }
        }

        // instantiating the Recycler
        makeRecyclerAdapter()

        // starting the search by clicking on the image
        imgSearch.setOnClickListener {
            searchCharacter(edtSearchName.text.toString())
        }

        // starting the search by keyboard
        edtSearchName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchCharacter(edtSearchName.text.toString())
            }
            true
        }
    }

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
