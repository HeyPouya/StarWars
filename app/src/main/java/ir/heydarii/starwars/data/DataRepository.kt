package ir.heydarii.starwars.data

import io.reactivex.Single
import ir.heydarii.starwars.pojo.CharacterSearchResponse
import ir.heydarii.starwars.retrofit.RetrofitMainInterface

class DataRepository(mainInterface: RetrofitMainInterface) {

    private val networkRepository = NetworkRepository(mainInterface)


    fun searchCharacterName(charachterName: String): Single<CharacterSearchResponse> {
        return networkRepository.searchCharacterName(charachterName)
    }
}