package ir.heydarii.starwars.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

/**
 * We use VieModelFactory to pass viewModels the data repository object
 */
class ViewModelFactory @Inject constructor(val creator: Map<KClass<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        for ((key, value) in creator) {
            if (modelClass.isAssignableFrom(key as Class<T>))
                return value as T
        }
        throw IllegalArgumentException("ViewModel is not provided by any modules : ${modelClass.name}")
    }
}