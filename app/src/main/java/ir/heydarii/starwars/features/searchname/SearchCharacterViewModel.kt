package ir.heydarii.starwars.features.searchname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import ir.heydarii.starwars.base.BaseViewModel
import ir.heydarii.starwars.data.DataRepository
import ir.heydarii.starwars.pojo.CharacterSearchResult

/**
 * ViewModel of the Search Name View
 * Fetches data and passes them to the view
 */
class SearchCharacterViewModel(private val dataRepository: DataRepository) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    private val searchNameData = MutableLiveData<List<CharacterSearchResult>>()

    /**
     * Fetches Character data with the given name
     */
    fun searchCharacterName(characterName: String): LiveData<List<CharacterSearchResult>> {
        disposable.add(
            dataRepository.searchCharacterName(characterName)
                .subscribe({
                    searchNameData.value = it.results
                }, {
                    Logger.d(it)
                    //TODO : Some Error handling
                })
        )
        return searchNameData
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}