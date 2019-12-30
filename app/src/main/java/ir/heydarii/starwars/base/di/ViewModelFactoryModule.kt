package ir.heydarii.starwars.base.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ir.heydarii.starwars.base.ViewModelFactory

/**
 * ViewModelFactoryModule to provide ViewModelFactory
 */
@Module
abstract class ViewModelFactoryModule {

    /**
     * Provides ViewModelFactory
     */
    @Binds
    abstract fun viewModelFactoryProvider(
        viewModelFactory: ViewModelFactory
    ): ViewModelProvider.Factory
}
