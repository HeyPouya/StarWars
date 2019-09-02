package ir.heydarii.starwars.features.details

import android.os.Bundle
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
import ir.heydarii.starwars.features.details.filmsadapter.FilmsRecyclerAdapter
import ir.heydarii.starwars.features.details.speciesadapter.SpeciesRecyclerAdapter
import ir.heydarii.starwars.pojo.FilmsDetailsResponse
import ir.heydarii.starwars.pojo.SpeciesDetailsResponse
import kotlinx.android.synthetic.main.activity_character_details.*
import kotlin.math.roundToInt

class CharacterDetailsActivity : BaseActivity() {

    lateinit var viewModel: CharacterDetailsViewModel
    private val speciesList = ArrayList<SpeciesDetailsResponse>()
    private lateinit var speciesAdapter: SpeciesRecyclerAdapter
    private val filmsList = ArrayList<FilmsDetailsResponse>()
    lateinit var filmsAdapter: FilmsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val url = intent.getStringExtra(Consts.URL)

        val viewModelFactory =
            ViewModelFactory(DataRepository((application as BaseApplication).mainInterface))
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CharacterDetailsViewModel::class.java)

        setUpSpeciesRecycler()
        setUpFilmsRecycler()


        viewModel.getDetails(url)

        viewModel.characterDetailsResponse.observe(this, Observer {
            txtName.text = getString(R.string.character_name_is, it.name)
            txtBirthDate.text = getString(R.string.character_birth_date_is, it.birth_year)

            txtHeight.text = getString(
                R.string.character_height_is,
                it.height,
                getFeet(it.height),
                getInch(it.height)
            )
        })

        viewModel.planetDetailsResponse.observe(this, Observer {
            txtPlanet.text = getString(R.string.character_planet_name_is, it.name)
            txtPopulation.text = getString(R.string.planet_population_is, it.population)
        })

        viewModel.speciesDetailsResponse.observe(this, Observer {
            speciesList.add(it)
            speciesAdapter.notifyItemInserted(speciesList.lastIndex)
        })

        viewModel.filmsDetailsResponse.observe(this, Observer {
            filmsList.add(it)
            filmsAdapter.notifyItemInserted(filmsList.lastIndex)
        })
    }

    private fun setUpFilmsRecycler() {
        filmsAdapter = FilmsRecyclerAdapter(filmsList)
        recyclerFilms.adapter = filmsAdapter
        recyclerFilms.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun setUpSpeciesRecycler() {
        speciesAdapter = SpeciesRecyclerAdapter(speciesList)
        recyclerSpecies.adapter = speciesAdapter
        recyclerSpecies.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }

    private fun getInch(height: String): String {
        val inch = height.toFloat() / 2.54
        return inch.roundToInt().toString()
    }

    private fun getFeet(height: String): String {
        val feet = height.toFloat() / 30.48
        return feet.roundToInt().toString()
    }
}
