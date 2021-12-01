package ir.heydarii.starwars.repository

import ir.heydarii.starwars.pojo.*
import ir.heydarii.starwars.repository.network.RetrofitMainInterface
import javax.inject.Inject

/**
 * All Observables are gathered here to be used as the data layer
 */
class DataRepository @Inject constructor(private val networkRepository: RetrofitMainInterface) {

    suspend fun searchCharacterName(characterName: String): CharacterSearchResponse {
        return networkRepository.searchCharacterName(characterName)
    }

    suspend fun getCharacterDetails(url: String): CharacterDetailsResponse {
        return networkRepository.getCharacterDetail(url)
    }

    suspend fun getPlanetDetails(url: String): PlanetDetailsResponse {
        return networkRepository.getPlanetDetails(url)
    }

    suspend fun getSpeciesDetails(url: String): SpeciesDetailsResponse {
        return networkRepository.getSpeciesDetails(url)
    }

    suspend fun getFilmsDetails(url: String): MoviesDetailsResponse {
        return networkRepository.getFilmsDetails(url)
    }
}
