package ir.heydarii.starwars.features.details.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.heydarii.starwars.base.di.ViewModelKey
import ir.heydarii.starwars.features.details.CharacterDetailsViewModel

@Module
abstract class CharacterDetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailsViewModel::class)
    abstract fun bindViewModel(viewModel: CharacterDetailsViewModel): ViewModel

}