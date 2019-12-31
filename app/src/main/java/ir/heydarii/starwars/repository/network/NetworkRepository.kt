package ir.heydarii.starwars.repository.network

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.heydarii.starwars.pojo.* // ktlint-disable no-wildcard-imports
import javax.inject.Inject

/**
 * All network Observables are configured here
 */
class NetworkRepository @Inject constructor(private val mainInterface: RetrofitMainInterface) {

    fun searchCharacterName(characterName: String): Single<CharacterSearchResponse> {
        return mainInterface.searchCharacterName(characterName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCharacterDetails(url: String): Single<CharacterDetailsResponse> {
        return mainInterface.getCharacterDetail(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getPlanetDetails(url: String): Single<PlanetDetailsResponse> {
        return mainInterface.getPlanetDetails(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSpeciesDetails(url: String): Single<SpeciesDetailsResponse> {
        return mainInterface.getSpeciesDetails(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getFilmsDetails(url: String): Single<MoviesDetailsResponse> {
        return mainInterface.getFilmsDetails(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
