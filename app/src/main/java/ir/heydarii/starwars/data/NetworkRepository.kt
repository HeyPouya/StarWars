package ir.heydarii.starwars.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.heydarii.starwars.pojo.CharacterDetailsResponse
import ir.heydarii.starwars.pojo.CharacterSearchResponse
import ir.heydarii.starwars.pojo.PlanetDetailsResponse
import ir.heydarii.starwars.pojo.SpeciesDetailsResponse
import ir.heydarii.starwars.retrofit.RetrofitMainInterface

class NetworkRepository(private val mainInterface: RetrofitMainInterface) {


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
}