package ir.heydarii.starwars.base.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import ir.heydarii.starwars.base.BaseApplication
import javax.inject.Singleton

/**
 * Dagger component to provide Retrofit interfaces
 */
@Singleton
@Component(modules = [RetrofitModule::class, ActivityBuilderModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}