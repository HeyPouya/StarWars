package ir.heydarii.starwars.features.searchname.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.heydarii.starwars.base.di.ViewModelKey
import ir.heydarii.starwars.features.searchname.SearchCharacterViewModel

/**
 * A module to let Dagger inject dependencies into SearchCharacterViewModel
 */
@Module
abstract class SearchCharacterViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchCharacterViewModel::class)
    abstract fun bindViewModel(viewModel: SearchCharacterViewModel): ViewModel
}