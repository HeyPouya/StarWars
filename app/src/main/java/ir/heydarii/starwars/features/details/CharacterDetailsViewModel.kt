package ir.heydarii.starwars.features.details

import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import ir.heydarii.starwars.base.BaseViewModel
import ir.heydarii.starwars.data.DataRepository
import ir.heydarii.starwars.pojo.CharacterDetailsResponse
import ir.heydarii.starwars.pojo.PlanetDetailsResponse
import ir.heydarii.starwars.pojo.SpeciesDetailsResponse

class CharacterDetailsViewModel(private val repository: DataRepository) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    val characterDetailsResponse = MutableLiveData<CharacterDetailsResponse>()
    val planetDetailsResponse = MutableLiveData<PlanetDetailsResponse>()
    val speciesDetailsResponse = MutableLiveData<SpeciesDetailsResponse>()

    fun getDetails(url: String) {
        disposable.add(repository.getCharacterDetails(url)
            .flatMap {
                characterDetailsResponse.value = it
                getSpeciesData(it.species)
//                getFilmsData(it.films)
                repository.getPlanetDetails(it.homeworld)
            }
            .subscribe({
                planetDetailsResponse.value = it
            }, {
                Logger.d(it)
            })
        )
    }

    private fun getSpeciesData(species: List<String>) {
        species.forEach {
            disposable.add(repository.getSpeciesDetails(it)
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