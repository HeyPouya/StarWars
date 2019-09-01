package ir.heydarii.starwars.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.heydarii.starwars.data.DataRepository
import ir.heydarii.starwars.features.details.CharacterDetailsViewModel
import ir.heydarii.starwars.features.searchname.MainViewModel

class ViewModelFactory(private val dataRepository: DataRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(dataRepository) as T
            modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java) -> CharacterDetailsViewModel(
                dataRepository
            ) as T
            else -> throw IllegalStateException("ViewModel is not provided")
        }
    }
}