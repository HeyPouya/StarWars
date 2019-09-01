package ir.heydarii.starwars.features.details

import io.reactivex.disposables.CompositeDisposable
import ir.heydarii.starwars.base.BaseViewModel
import ir.heydarii.starwars.data.DataRepository

class CharacterDetailsViewModel(repository: DataRepository) : BaseViewModel() {

    private val disposable = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}