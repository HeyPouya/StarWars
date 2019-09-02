package ir.heydarii.starwars.data

import io.reactivex.Single
import ir.heydarii.starwars.pojo.*
import ir.heydarii.starwars.retrofit.RetrofitMainInterface

/*
    All Observables are gathered here to be used as the data layer
 */
class DataRepository(mainInterface: RetrofitMainInterface) {

    private val networkRepository = NetworkRepository(mainInterface)


    fun searchCharacterName(characterName: String): Single<CharacterSearchResponse> {
        return networkRepository.searchCharacterName(characterName)
    }

    fun getCharacterDetails(url: String): Single<CharacterDetailsResponse> {
        return networkRepository.getCharacterDetails(url)
    }

    fun getPlanetDetails(url: String): Single<PlanetDetailsResponse> {
        return networkRepository.getPlanetDetails(url)
    }

    fun getSpeciesDetails(url: String): Single<SpeciesDetailsResponse> {
        return networkRepository.getSpeciesDetails(url)
    }

    fun getFilmsDetails(url: String): Single<FilmsDetailsResponse> {
        return networkRepository.getFilmsDetails(url)
    }
}