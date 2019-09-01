package ir.heydarii.starwars.retrofit

import io.reactivex.Single
import ir.heydarii.starwars.pojo.CharacterDetailsResponse
import ir.heydarii.starwars.pojo.CharacterSearchResponse
import ir.heydarii.starwars.pojo.PlanetDetailsResponse
import ir.heydarii.starwars.pojo.SpeciesDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RetrofitMainInterface {

    @GET("people/?")
    fun searchCharacterName(@Query("search") characterName: String): Single<CharacterSearchResponse>

    @GET
    fun getCharacterDetail(@Url url: String): Single<CharacterDetailsResponse>

    @GET
    fun getPlanetDetails(@Url url: String): Single<PlanetDetailsResponse>

    @GET
    fun getSpeciesDetails(@Url url: String): Single<SpeciesDetailsResponse>
}