package ir.heydarii.starwars.features.searchname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import ir.heydarii.starwars.base.BaseViewModel
import ir.heydarii.starwars.repository.DataRepository
import ir.heydarii.starwars.pojo.CharacterSearchResult
import ir.heydarii.starwars.utils.ErrorTypes
import javax.inject.Inject

/**
 * ViewModel of the Search Name View
 * Fetches data and passes them to the view
 */
class SearchCharacterViewModel @Inject constructor(val dataRepository: DataRepository) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    private val searchNameData = MutableLiveData<List<CharacterSearchResult>>()

    /**
     * Fetches Character data with the given name
     */
    fun searchCharacterName(characterName: String) {
        disposable.add(
                dataRepository.searchCharacterName(characterName)
                        .subscribe({
                            searchNameData.value = it.results
                        }, {
                            Logger.d(it)
                            errorData.value = ErrorTypes.ERROR_RECEIVING_DATA
                        }))
    }

    /**
     * Returns immutable live data
     */
    fun searchResultData(): LiveData<List<CharacterSearchResult>> = searchNameData

    /**
     * We make sure to clear everything up
     */
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}