package ir.heydarii.starwars.features.searchname.response

sealed class SearchCharacterResource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : SearchCharacterResource<T>(data)
    class Loading<T>(data: T? = null) : SearchCharacterResource<T>(data)
    class Error<T>(message: String, data: T? = null) : SearchCharacterResource<T>(data, message)
}