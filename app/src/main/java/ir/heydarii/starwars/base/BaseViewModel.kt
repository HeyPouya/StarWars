package ir.heydarii.starwars.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.heydarii.starwars.utils.ErrorTypes

/**
 * This class will be used to configure all ViewModels if needed
 */
open class BaseViewModel : ViewModel() {

    protected val errorData = MutableLiveData<ErrorTypes>()

    fun getErrors(): LiveData<ErrorTypes> = errorData
}