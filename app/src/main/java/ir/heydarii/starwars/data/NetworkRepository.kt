package ir.heydarii.starwars.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.heydarii.starwars.pojo.CharacterSearchResponse
import ir.heydarii.starwars.retrofit.RetrofitMainInterface

class NetworkRepository(private val mainInterface: RetrofitMainInterface) {


    fun searchCharacterName(characterName: String): Single<CharacterSearchResponse> {
        return mainInterface.searchCharacterName(characterName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}