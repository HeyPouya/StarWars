package ir.heydarii.starwars.features.searchname

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseActivity
import ir.heydarii.starwars.base.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        imgSearch.setOnClickListener {
            loading.visibility = View.VISIBLE

        }

    }
}
