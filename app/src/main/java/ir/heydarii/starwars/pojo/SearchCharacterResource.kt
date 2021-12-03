package ir.heydarii.starwars.pojo

/**
 * Response resource for
 */
sealed class SearchCharacterResource<T>(val data: T? = null, val message: String? = null) {
    /**
     * When we get the response successfully
     */
    class Success<T>(data: T) : SearchCharacterResource<T>(data)

    /**
     * When we want to show the loading
     */
    class Loading<T>(data: T? = null) : SearchCharacterResource<T>(data)

    /**
     * When there is an error while getting the data
     */
    class Error<T>(message: String, data: T? = null) : SearchCharacterResource<T>(data, message)
}
