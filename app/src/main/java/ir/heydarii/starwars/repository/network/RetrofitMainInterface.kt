package ir.heydarii.starwars.repository.network

import ir.heydarii.starwars.pojo.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Retrofit interface
 */
interface RetrofitMainInterface {

    @GET("people/?")
    suspend fun searchCharacterName(@Query("search") characterName: String): CharacterSearchResponse

    @GET
    suspend fun getCharacterDetail(@Url url: String): CharacterDetailsResponse

    @GET
    suspend fun getPlanetDetails(@Url url: String): PlanetDetailsResponse

    @GET
    suspend fun getSpeciesDetails(@Url url: String): SpeciesDetailsResponse

    @GET
    suspend fun getFilmsDetails(@Url url: String): MoviesDetailsResponse
}
