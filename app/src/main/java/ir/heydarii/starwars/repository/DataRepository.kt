package ir.heydarii.starwars.repository

import io.reactivex.Single
import ir.heydarii.starwars.pojo.* // ktlint-disable no-wildcard-imports
import ir.heydarii.starwars.repository.network.NetworkRepository
import javax.inject.Inject

/**
 * All Observables are gathered here to be used as the data layer
 */
class DataRepository @Inject constructor(private val networkRepository: NetworkRepository) {

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

    fun getFilmsDetails(url: String): Single<MoviesDetailsResponse> {
        return networkRepository.getFilmsDetails(url)
    }
}
