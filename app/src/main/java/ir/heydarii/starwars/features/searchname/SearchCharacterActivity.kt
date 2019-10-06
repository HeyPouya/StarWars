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

class SearchCharacterActivity : BaseActivity() {

    lateinit var viewModel: SearchCharacterViewModel
    lateinit var adapter: SearchNameRecyclerAdapter
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

        //instantiating the Recycler
        makeRecyclerAdapter()

        //subscribing to search character response
        viewModel.searchNameData.observe(this, Observer {
            loading.visibility = View.INVISIBLE
            showRecycler(it)
        })

        //starting the search by clicking on the image
        imgSearch.setOnClickListener {
            loading.visibility = View.VISIBLE
            viewModel.searchCharacterName(edtSearchName.text.toString())
        }

        //starting the search by keyboard
        edtSearchName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                loading.visibility = View.VISIBLE
                viewModel.searchCharacterName(edtSearchName.text.toString())
            }
            true
        }

    }

    /**
     * Instantiates the recyclerView and adds onItemClickListener to it
     */
    private fun makeRecyclerAdapter() {
        adapter = SearchNameRecyclerAdapter(list) { url: String ->
            onCharacterSelected(url)
        }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    /**
     * onItemClickListener for the recyclerView.
     * It navigates the user to CharacterDetailsActivity
     */
    private fun onCharacterSelected(url: String) {
        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra(Consts.URL, url)
        startActivity(intent)
    }

    /**
     * Updates the recycler's data every time that new data arrives
     */
    private fun showRecycler(searchData: List<CharacterSearchResult>) {
        list.clear()
        list.addAll(searchData)
        adapter.notifyDataSetChanged()
    }
}
