package ir.heydarii.starwars.base

import android.app.Application
import ir.heydarii.starwars.base.di.DaggerRetrofitComponent
import ir.heydarii.starwars.retrofit.RetrofitMainInterface

/**
 * BaseApplication class to provide some needed dependencies
 */

class BaseApplication : Application() {

    //We provide retrofit interface here to have singleton and memory-leak-free retrofit instance
    lateinit var mainInterface: RetrofitMainInterface

    override fun onCreate() {
        super.onCreate()
        mainInterface = DaggerRetrofitComponent.create().getMainInterface()
    }
}