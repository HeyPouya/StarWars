package ir.heydarii.starwars.features.searchname

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseActivity
import ir.heydarii.starwars.base.BaseApplication
import ir.heydarii.starwars.base.ViewModelFactory
import ir.heydarii.starwars.data.DataRepository
import ir.heydarii.starwars.pojo.CharacterSearchResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: SearchNameRecyclerAdapter
    private val list = ArrayList<CharacterSearchResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory =
            ViewModelFactory(DataRepository((application as BaseApplication).mainInterface))
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        makeRecyclerAdapter()

        viewModel.searchNameData.observe(this, Observer {
            loading.visibility = View.INVISIBLE
            showRecycler(it)
        })

        imgSearch.setOnClickListener {
            loading.visibility = View.VISIBLE
            viewModel.searchCharacterName(edtSearchName.text.toString())
        }

    }

    private fun makeRecyclerAdapter() {
        adapter = SearchNameRecyclerAdapter(list)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun showRecycler(searchData: List<CharacterSearchResult>) {
        list.addAll(searchData)
        adapter.notifyDataSetChanged()
    }
}
