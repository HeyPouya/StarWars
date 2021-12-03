package ir.heydarii.starwars.features.searchname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.heydarii.starwars.base.BaseViewModel
import ir.heydarii.starwars.pojo.CharacterSearchResponse
import ir.heydarii.starwars.pojo.SearchCharacterResource
import ir.heydarii.starwars.repository.DataRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel of the SearchCharacter view
 */
@HiltViewModel
class SearchCharacterViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {

    private val _searchNameData =
        MutableLiveData<SearchCharacterResource<CharacterSearchResponse>>()
    val searchNameData: LiveData<SearchCharacterResource<CharacterSearchResponse>> =
        _searchNameData

    /**
     * Fetches Character data with the given name
     */
    fun searchCharacterName(characterName: String) {
        viewModelScope.runCatching {

            _searchNameData.postValue(SearchCharacterResource.Loading())

            launch {
                val data = dataRepository.searchCharacterName(characterName)
                _searchNameData.postValue(SearchCharacterResource.Success(data))
            }
        }.onFailure {
            it.printStackTrace()
            _searchNameData.postValue(SearchCharacterResource.Error(it.message.orEmpty()))
        }
    }
}
