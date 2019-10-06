package ir.heydarii.starwars.features.searchname

import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import ir.heydarii.starwars.base.BaseViewModel
import ir.heydarii.starwars.data.DataRepository
import ir.heydarii.starwars.pojo.CharacterSearchResult

class SearchCharacterViewModel(private val dataRepository: DataRepository) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    val searchNameData = MutableLiveData<List<CharacterSearchResult>>()

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
                            //TODO : Some Error handling
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}