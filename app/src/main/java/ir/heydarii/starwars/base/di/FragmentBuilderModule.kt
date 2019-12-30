package ir.heydarii.starwars.base.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.heydarii.starwars.features.details.CharacterDetailsFragment
import ir.heydarii.starwars.features.searchname.SearchCharacterFragment

/**
 * A module to build fragments and doing needed injections by dagger 2
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeSearchCharacterFragment(): SearchCharacterFragment

    @ContributesAndroidInjector
    abstract fun contributeCharacterDetailsFragment(): CharacterDetailsFragment
}
