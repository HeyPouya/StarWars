package ir.heydarii.starwars.features.details

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseActivity
import ir.heydarii.starwars.base.BaseApplication
import ir.heydarii.starwars.base.Consts
import ir.heydarii.starwars.base.ViewModelFactory
import ir.heydarii.starwars.data.DataRepository

class CharacterDetailsActivity : BaseActivity() {

    lateinit var viewModel: CharacterDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val url = intent.getStringExtra(Consts.URL)
        val species = intent.getStringExtra(Consts.SPECIES)

        val viewModelFactory =
            ViewModelFactory(DataRepository((application as BaseApplication).mainInterface))
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CharacterDetailsViewModel::class.java)


    }
}
