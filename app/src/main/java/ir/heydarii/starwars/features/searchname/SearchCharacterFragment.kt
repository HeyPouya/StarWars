package ir.heydarii.starwars.features.searchname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseApplication
import ir.heydarii.starwars.base.BaseFragment
import ir.heydarii.starwars.base.ViewModelFactory
import ir.heydarii.starwars.data.DataRepository
import ir.heydarii.starwars.features.searchname.adapter.SearchNameDiffUtilsCallback
import ir.heydarii.starwars.pojo.CharacterSearchResult
import kotlinx.android.synthetic.main.activity_character_search.*

/**
 * User can search any StarWars character name here
 */
class SearchCharacterFragment : BaseFragment() {

    private lateinit var viewModel: SearchCharacterViewModel
    private lateinit var adapter: SearchNameRecyclerAdapter


    /**
     * inflating the view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_character_search, container, false)
    }

    /**
     * All codes are here
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModelFactory to pass the dataRepository to viewModel
        val viewModelFactory =
                ViewModelFactory(DataRepository((activity?.application as BaseApplication).mainInterface))

        //instantiating the viewModel
        viewModel =
                ViewModelProvider(this, viewModelFactory).get(SearchCharacterViewModel::class.java)

        //subscribing to get errors in ViewModel
        viewModel.getErrors().observe(this, Observer {
            loading.visibility = View.GONE
            showError(rootView, getString(R.string.some_errors_while_fetching_data)) {
                searchCharacter(edtSearchName.text.toString())
            }
        })

        viewModel.searchResultData().observe(this, Observer {
            loading.visibility = View.INVISIBLE
            showResultsInRecycler(it)
        })

        //instantiating the Recycler
        makeRecyclerAdapter()

        //starting the search by clicking on the image
        imgSearch.setOnClickListener {
            searchCharacter(edtSearchName.text.toString())
        }

        //starting the search by keyboard
        edtSearchName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchCharacter(edtSearchName.text.toString())
            }
            true
        }

    }

    private fun searchCharacter(characterName: String) {
        loading.visibility = View.VISIBLE
        viewModel.searchCharacterName(characterName)
    }

    private fun makeRecyclerAdapter() {
        adapter = SearchNameRecyclerAdapter(SearchNameDiffUtilsCallback()) { url: String -> onCharacterSelected(url) }
        recycler.adapter = adapter
    }

    private fun onCharacterSelected(url: String) {
        val action = SearchCharacterFragmentDirections.showCharacterDetailsAction(url)
        Navigation.findNavController(rootView).navigate(action)
    }

    private fun showResultsInRecycler(searchData: List<CharacterSearchResult>) {
        adapter.submitList(searchData)
    }
}
