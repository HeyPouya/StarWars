package ir.heydarii.starwars.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseFragment
import ir.heydarii.starwars.base.ViewModelFactory
import ir.heydarii.starwars.features.details.moviesadapter.MoviesRecyclerAdapter
import ir.heydarii.starwars.features.details.speciesadapter.SpeciesRecyclerAdapter
import ir.heydarii.starwars.pojo.CharacterDetailsResponse
import ir.heydarii.starwars.pojo.MoviesDetailsResponse
import ir.heydarii.starwars.pojo.PlanetDetailsResponse
import ir.heydarii.starwars.pojo.SpeciesDetailsResponse
import ir.heydarii.starwars.utils.CharacterResponseTypes.*
import kotlinx.android.synthetic.main.fragment_character_details.*
import javax.inject.Inject
import kotlin.math.roundToInt

/**
 * Shows details of a character
 */
class CharacterDetailsFragment : BaseFragment() {

    private lateinit var viewModel: CharacterDetailsViewModel
    private val speciesList = ArrayList<SpeciesDetailsResponse>()
    private lateinit var speciesAdapter: SpeciesRecyclerAdapter
    private val filmsList = ArrayList<MoviesDetailsResponse>()
    private lateinit var filmsAdapter: MoviesRecyclerAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    /**
     * Inflating the layout
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    /**
     * All codes are here
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getting the url to details data or throw an exception
        val url = arguments?.let {
            CharacterDetailsFragmentArgs.fromBundle(it).url
        } ?: throw IllegalArgumentException("Url must nor be null")


        //instantiating the viewModel
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CharacterDetailsViewModel::class.java)

        //starting the search by clicking on the image
        viewModel.getErrors().observe(this, Observer {
            showError(rootView, getString(R.string.some_errors_while_fetching_data)) {
                getDetails(url)
            }
        })

        imgBack.setOnClickListener { activity?.onBackPressed() }

        //instantiating the species recycler view
        setUpSpeciesRecycler()

        //instantiating the films recycler view
        setUpFilmsRecycler()

        //Asks viewModel to fetch data and subscribes to its response
        getDetails(url)

    }

    private fun getDetails(url: String) {
        viewModel.getDetails(url).observe(this, Observer { (type, value) ->
            when (type) {
                CHARACTER_DETAILS -> showCharacterDetails(value as CharacterDetailsResponse)
                PLANET_DETAILS -> showPlanetDetails(value as PlanetDetailsResponse)
                SPECIE_DETAILS -> showSpecieDetails(value as SpeciesDetailsResponse)
                MOVIE_DETAILS -> showMovieDetails(value as MoviesDetailsResponse)
            }
        })
    }

    private fun showMovieDetails(moviesDetails: MoviesDetailsResponse) {
        progressMovie.visibility = View.GONE
        filmsList.add(moviesDetails)
        filmsAdapter.notifyItemInserted(filmsList.lastIndex)
    }

    private fun showSpecieDetails(speciesDetails: SpeciesDetailsResponse) {
        progressDetails.visibility = View.GONE
        speciesList.add(speciesDetails)
        speciesAdapter.notifyItemInserted(speciesList.lastIndex)
    }

    private fun showPlanetDetails(planetDetails: PlanetDetailsResponse) {
        progressDetails.visibility = View.GONE
        txtPlanet.text = getString(R.string.character_planet_name_is, planetDetails.name)
        txtPopulation.text = getString(R.string.planet_population_is, planetDetails.population)

    }

    private fun showCharacterDetails(characterDetails: CharacterDetailsResponse) {
        txtName.text = characterDetails.name
        txtBirthDate.text = characterDetails.birth_year
        if (characterDetails.height.isDigitsOnly())
            txtHeight.text = getString(
                R.string.character_height_is,
                characterDetails.height,
                getFeet(characterDetails.height),
                getInch(characterDetails.height)
            )
    }

    private fun setUpFilmsRecycler() {
        filmsAdapter = MoviesRecyclerAdapter(filmsList)
        recyclerFilms.adapter = filmsAdapter
    }

    private fun setUpSpeciesRecycler() {
        speciesAdapter = SpeciesRecyclerAdapter(speciesList)
        recyclerSpecies.adapter = speciesAdapter
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
