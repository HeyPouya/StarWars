package ir.heydarii.starwars.base.di

import dagger.Component
import ir.heydarii.starwars.retrofit.RetrofitMainInterface
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {

    fun getMainInterface(): RetrofitMainInterface
}