package ir.heydarii.starwars.base.di

import dagger.Component
import ir.heydarii.starwars.retrofit.RetrofitMainInterface
import javax.inject.Singleton

/**
 * Dagger component to provide Retrofit interfaces
 */
@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {

    /**
     * Provides retrofit interface for the application
     */
    fun getMainInterface(): RetrofitMainInterface
}