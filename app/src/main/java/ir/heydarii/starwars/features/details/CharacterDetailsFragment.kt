package ir.heydarii.starwars.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseFragment
import ir.heydarii.starwars.databinding.FragmentCharacterDetailsBinding
import ir.heydarii.starwars.features.details.moviesadapter.MoviesRecyclerAdapter
import ir.heydarii.starwars.features.details.speciesadapter.SpeciesRecyclerAdapter
import ir.heydarii.starwars.pojo.CharacterDetailsResponse
import ir.heydarii.starwars.pojo.MoviesDetailsResponse
import ir.heydarii.starwars.pojo.PlanetDetailsResponse
import ir.heydarii.starwars.pojo.SpeciesDetailsResponse
import ir.heydarii.starwars.utils.CharacterResponseTypes.*
import kotlin.math.roundToInt

/**
 * Shows details of a character
 */
@AndroidEntryPoint
class CharacterDetailsFragment : BaseFragment() {

    private val speciesList = ArrayList<SpeciesDetailsResponse>()
    private lateinit var speciesAdapter: SpeciesRecyclerAdapter
    private val filmsList = ArrayList<MoviesDetailsResponse>()
    private lateinit var filmsAdapter: MoviesRecyclerAdapter
    private val viewModel by viewModels<CharacterDetailsViewModel>()
    private lateinit var binding: FragmentCharacterDetailsBinding

    /**
     * Inflating the layout
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater)
        return binding.root
    }

    /**
     * All codes are here
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the url to details data or throw an exception
        val url = arguments?.let {
            CharacterDetailsFragmentArgs.fromBundle(it).url
        } ?: throw IllegalArgumentException("Url must nor be null")

        // starting the search by clicking on the image
        viewModel.getErrors().observe(viewLifecycleOwner) {
            showError(requireView(), getString(R.string.some_errors_while_fetching_data)) {
                getDetails(url)
            }
        }

        binding.imgBack.setOnClickListener { activity?.onBackPressed() }

        // instantiating the species recycler view
        setUpSpeciesRecycler()

        // instantiating the films recycler view
        setUpFilmsRecycler()

        // Asks viewModel to fetch data and subscribes to its response
        getDetails(url)
    }

    private fun getDetails(url: String) {
        viewModel.getDetails(url)
        viewModel.detailsResponseData.observe(viewLifecycleOwner) { (type, value) ->
            when (type) {
                CHARACTER_DETAILS -> showCharacterDetails(value as CharacterDetailsResponse)
                PLANET_DETAILS -> showPlanetDetails(value as PlanetDetailsResponse)
                SPECIE_DETAILS -> showSpecieDetails(value as SpeciesDetailsResponse)
                MOVIE_DETAILS -> showMovieDetails(value as MoviesDetailsResponse)
            }
        }
    }

    private fun showMovieDetails(moviesDetails: MoviesDetailsResponse) {
        binding.progressMovie.visibility = View.GONE
        filmsList.add(moviesDetails)
        filmsAdapter.notifyItemInserted(filmsList.lastIndex)
    }

    private fun showSpecieDetails(speciesDetails: SpeciesDetailsResponse) {
        binding.progressDetails.visibility = View.GONE
        speciesList.add(speciesDetails)
        speciesAdapter.notifyItemInserted(speciesList.lastIndex)
    }

    private fun showPlanetDetails(planetDetails: PlanetDetailsResponse) {
        binding.progressDetails.visibility = View.GONE
        binding.txtPlanet.text = getString(R.string.character_planet_name_is, planetDetails.name)
        binding.txtPopulation.text =
            getString(R.string.planet_population_is, planetDetails.population)
    }

    private fun showCharacterDetails(characterDetails: CharacterDetailsResponse) {
        binding.txtName.text = characterDetails.name
        binding.txtBirthDate.text = characterDetails.birth_year
        if (characterDetails.height.isDigitsOnly())
            binding.txtHeight.text = getString(
                R.string.character_height_is,
                characterDetails.height,
                getFeet(characterDetails.height),
                getInch(characterDetails.height)
            )
    }

    private fun setUpFilmsRecycler() {
        filmsAdapter = MoviesRecyclerAdapter(filmsList)
        binding.recyclerFilms.adapter = filmsAdapter
    }

    private fun setUpSpeciesRecycler() {
        speciesAdapter = SpeciesRecyclerAdapter(speciesList)
        binding.recyclerSpecies.adapter = speciesAdapter
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
