package ir.heydarii.starwars.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.heydarii.starwars.base.BaseViewModel
import ir.heydarii.starwars.repository.DataRepository
import ir.heydarii.starwars.utils.CharacterResponseTypes
import ir.heydarii.starwars.utils.ErrorTypes
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel of the Details View
 * Fetches data and passes them to the view
 */
@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val repository: DataRepository
) : BaseViewModel() {

    private val _detailsResponseData = MutableLiveData<Pair<CharacterResponseTypes, Any>>()
    val detailsResponseData: LiveData<Pair<CharacterResponseTypes, Any>> = _detailsResponseData

    /**
     * Fetches details about character
     * it also contains these urls :
     *
     * planet details url
     * species details url
     * films details urls
     */
    fun getDetails(url: String) {
        viewModelScope.runCatching {
            launch {
                val data = repository.getCharacterDetails(url)
                _detailsResponseData.postValue(CharacterResponseTypes.CHARACTER_DETAILS to data)

                // fetching the species details
                getSpeciesData(data.species)

                // fetching the films details
                getFilmsData(data.films)

                // fetching the planet details
                getPlanetDetails(data.homeworld)

            }
        }.onFailure {
            it.printStackTrace()
            errorData.postValue(ErrorTypes.ERROR_RECEIVING_DATA)

        }
    }

    private fun getPlanetDetails(homeWorld: String) = viewModelScope.launch {
        val data = repository.getPlanetDetails(homeWorld)
        _detailsResponseData.postValue(CharacterResponseTypes.PLANET_DETAILS to data)
    }

    private fun getFilmsData(films: List<String>) = viewModelScope.launch {
        films.forEach { film ->
            val data = repository.getFilmsDetails(film)
            _detailsResponseData.postValue(CharacterResponseTypes.MOVIE_DETAILS to data)

        }
    }

    private fun getSpeciesData(species: List<String>) = viewModelScope.launch {
        species.forEach { specie ->
            val data = repository.getSpeciesDetails(specie)
            _detailsResponseData.postValue(CharacterResponseTypes.SPECIE_DETAILS to data)
        }
    }
}
