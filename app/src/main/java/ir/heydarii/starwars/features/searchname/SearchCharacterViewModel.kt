package ir.heydarii.starwars.features.searchname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import ir.heydarii.starwars.base.BaseViewModel
import ir.heydarii.starwars.features.searchname.response.SearchCharacterResource
import ir.heydarii.starwars.pojo.CharacterSearchResponse
import ir.heydarii.starwars.repository.DataRepository
import javax.inject.Inject

/**
 * ViewModel of the Search Name View
 * Fetches data and passes them to the view
 */
@HiltViewModel
class SearchCharacterViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    private val searchNameData = MutableLiveData<SearchCharacterResource<CharacterSearchResponse>>()

    /**
     * Fetches Character data with the given name
     */
    fun searchCharacterName(characterName: String) {
        searchNameData.value = SearchCharacterResource.Loading()
        disposable.add(
            dataRepository.searchCharacterName(characterName)
                .subscribe({
                    searchNameData.value = SearchCharacterResource.Success(it)
                }, {
                    Logger.d(it)
                    searchNameData.value = SearchCharacterResource.Error(it.message.orEmpty())
                })
        )
    }

    /**
     * Returns immutable live data
     */
    fun searchResultData(): LiveData<SearchCharacterResource<CharacterSearchResponse>> =
        searchNameData

    /**
     * We make sure to clear everything up
     */
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
