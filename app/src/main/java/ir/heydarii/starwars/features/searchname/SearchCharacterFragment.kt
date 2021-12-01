package ir.heydarii.starwars.features.searchname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseFragment
import ir.heydarii.starwars.databinding.FragmentCharacterSearchBinding
import ir.heydarii.starwars.features.searchname.adapter.SearchCharacterDiffUtilsCallback
import ir.heydarii.starwars.features.searchname.adapter.SearchNameRecyclerAdapter
import ir.heydarii.starwars.features.searchname.response.SearchCharacterResource
import ir.heydarii.starwars.pojo.CharacterSearchResult

/**
 * User can search any StarWars character name here
 */
@AndroidEntryPoint
class SearchCharacterFragment : BaseFragment() {

    private val viewModel by viewModels<SearchCharacterViewModel>()
    private lateinit var adapter: SearchNameRecyclerAdapter
    private lateinit var binding: FragmentCharacterSearchBinding

    /**
     * inflating the view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterSearchBinding.inflate(inflater)
        return binding.root
    }

    /**
     * All codes are here
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.searchResultData().observe(viewLifecycleOwner) {

            when (it) {
                is SearchCharacterResource.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is SearchCharacterResource.Error -> {
                    binding.loading.visibility = View.INVISIBLE
                    showError(requireView(), getString(R.string.some_errors_while_fetching_data)) {
                        searchCharacter(binding.edtSearchName.text.toString())
                    }
                }
                is SearchCharacterResource.Success -> {
                    binding.loading.visibility = View.INVISIBLE
                    showResultsInRecycler(it.data?.results?.toMutableList())
                }
            }
        }

        // instantiating the Recycler
        makeRecyclerAdapter()

        // starting the search by clicking on the image
        binding.imgSearch.setOnClickListener {
            searchCharacter(binding.edtSearchName.text.toString())
        }

        // starting the search by keyboard
        binding.edtSearchName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchCharacter(binding.edtSearchName.text.toString())
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
        binding.recycler.adapter = adapter
    }

    private fun onCharacterSelected(url: String) {
        val action = SearchCharacterFragmentDirections.showCharacterDetailsAction(url)
        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun showResultsInRecycler(searchData: List<CharacterSearchResult>?) {
        adapter.submitList(searchData)
    }
}
