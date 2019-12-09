package ir.heydarii.starwars.base.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.heydarii.starwars.features.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun mainActivityContributer(): MainActivity
}