package ir.heydarii.starwars.features.searchname

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseActivity
import ir.heydarii.starwars.base.BaseApplication
import ir.heydarii.starwars.base.Consts
import ir.heydarii.starwars.base.ViewModelFactory
import ir.heydarii.starwars.data.DataRepository
import ir.heydarii.starwars.features.details.CharacterDetailsActivity
import ir.heydarii.starwars.pojo.CharacterSearchResult
import kotlinx.android.synthetic.main.activity_main.*

/**
 * User can search any StarWars character name here
 */
class SearchCharacterActivity : BaseActivity() {

    private lateinit var viewModel: SearchCharacterViewModel
    private lateinit var adapter: SearchNameRecyclerAdapter
    private val list = ArrayList<CharacterSearchResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //viewModelFactory to pass the dataRepository to viewModel
        val viewModelFactory =
            ViewModelFactory(DataRepository((application as BaseApplication).mainInterface))

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
            .observe(this, Observer {
                loading.visibility = View.INVISIBLE
                showResultsInRecycler(it)
            })
    }

    private fun makeRecyclerAdapter() {
        adapter = SearchNameRecyclerAdapter(list) { url: String -> onCharacterSelected(url) }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun onCharacterSelected(url: String) {
        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra(Consts.URL, url)
        startActivity(intent)
    }

    private fun showResultsInRecycler(searchData: List<CharacterSearchResult>) {
        list.clear()
        list.addAll(searchData)
        adapter.notifyDataSetChanged()
    }
}
