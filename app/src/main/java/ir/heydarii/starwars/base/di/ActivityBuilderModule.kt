package ir.heydarii.starwars.base.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.heydarii.starwars.features.MainActivity
import ir.heydarii.starwars.features.searchname.di.SearchCharacterViewModelModule


/**
 * A module to make some sub components for each activity
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [SearchCharacterViewModelModule::class])
    abstract fun mainActivityContributer(): MainActivity
}