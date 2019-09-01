package ir.heydarii.starwars.retrofit

import io.reactivex.Observable
import io.reactivex.Single
import ir.heydarii.starwars.pojo.CharacterSearchResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitMainInterface {

    @GET("people/?")
    fun searchCharacterName(@Query("search") characterName: String): Single<CharacterSearchResponse>
}