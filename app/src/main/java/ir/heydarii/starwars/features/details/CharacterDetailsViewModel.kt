package ir.heydarii.starwars.features.details

import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import ir.heydarii.starwars.base.BaseViewModel
import ir.heydarii.starwars.data.DataRepository
import ir.heydarii.starwars.pojo.CharacterDetailsResponse
import ir.heydarii.starwars.pojo.FilmsDetailsResponse
import ir.heydarii.starwars.pojo.PlanetDetailsResponse
import ir.heydarii.starwars.pojo.SpeciesDetailsResponse

class CharacterDetailsViewModel(private val repository: DataRepository) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    val characterDetailsResponse = MutableLiveData<CharacterDetailsResponse>()
    val planetDetailsResponse = MutableLiveData<PlanetDetailsResponse>()
    val speciesDetailsResponse = MutableLiveData<SpeciesDetailsResponse>()
    val filmsDetailsResponse = MutableLiveData<FilmsDetailsResponse>()

    /**
     * Fetches details about character
     * it also contains these urls :
     *
     * planet details url
     * species details url
     * films details urls
     */
    fun getDetails(url: String) {
        disposable.add(repository.getCharacterDetails(url)
            .flatMap {

                //emitting the characterDetails to activity
                characterDetailsResponse.value = it

                //fetching the species details
                getSpeciesData(it.species)

                //fetching the films details
                getFilmsData(it.films)

                //flatMapping to get PlanetDetails
                repository.getPlanetDetails(it.homeworld)
            }
            .subscribe({
                planetDetailsResponse.value = it
            }, {
                Logger.d(it)
            })
        )
    }

    /**
     * Fetches all films url data one by one
     */
    private fun getFilmsData(films: List<String>) {
        films.forEach {
            disposable.add(
                repository.getFilmsDetails(it)
                    .subscribe({
                        filmsDetailsResponse.value = it
                    }, {
                        Logger.d(it)
                    })
            )
        }
    }

    /**
     * Fetches all species url data one by one
     */
    private fun getSpeciesData(species: List<String>) {
        species.forEach {
            disposable.add(
                repository.getSpeciesDetails(it)
                    .subscribe({
                        speciesDetailsResponse.value = it
                    }, {
                        Logger.d(it)
                    })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}