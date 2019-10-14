package ir.heydarii.starwars.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import ir.heydarii.starwars.base.BaseViewModel
import ir.heydarii.starwars.data.DataRepository
import ir.heydarii.starwars.utils.CharacterResponseTypes

/**
 * ViewModel of the Details View
 * Fetches data and passes them to the view
 */
class CharacterDetailsViewModel(private val repository: DataRepository) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    private val detailsResponseData = MutableLiveData<Pair<CharacterResponseTypes, Any>>()

    /**
     * Fetches details about character
     * it also contains these urls :
     *
     * planet details url
     * species details url
     * films details urls
     */
    fun getDetails(url: String): LiveData<Pair<CharacterResponseTypes, Any>> {
        disposable.add(
            repository.getCharacterDetails(url)
                .subscribe({

                    //emitting the characterDetails to activity
                    detailsResponseData.value = CharacterResponseTypes.CHARACTER_DETAILS to it

                    //fetching the species details
                    getSpeciesData(it.species)

                    //fetching the films details
                    getFilmsData(it.films)

                    //fetching the planet details
                    getPlanetDetails(it.homeworld)

                }, {
                    Logger.d(it)
                    //TODO : Some Error handling
                })
        )
        return detailsResponseData
    }

    private fun getPlanetDetails(homeWorld: String) {
        disposable.add(
            repository.getPlanetDetails(homeWorld)
                .subscribe({
                    detailsResponseData.value = CharacterResponseTypes.PLANET_DETAILS to it
                }, {
                    Logger.d(it)
                    //TODO : Some Error handling
                })
        )

    }

    private fun getFilmsData(films: List<String>) {
        films.forEach { film ->
            disposable.add(
                repository.getFilmsDetails(film)
                    .subscribe({
                        detailsResponseData.value = CharacterResponseTypes.MOVIE_DETAILS to it
                    }, {
                        Logger.d(it)
                        //TODO : Some Error handling
                    })
            )
        }
    }

    private fun getSpeciesData(species: List<String>) {
        species.forEach { specie ->
            disposable.add(
                repository.getSpeciesDetails(specie)
                    .subscribe({
                        detailsResponseData.value = CharacterResponseTypes.SPECIE_DETAILS to it
                    }, {
                        Logger.d(it)
                        //TODO : Some Error handling
                    })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}