package ir.heydarii.starwars.base

import android.app.Application
import ir.heydarii.starwars.base.di.DaggerRetrofitComponenet
import ir.heydarii.starwars.retrofit.RetrofitMainInterface

class BaseApplication : Application() {

    lateinit var mainInterface: RetrofitMainInterface


    override fun onCreate() {
        super.onCreate()
        mainInterface = DaggerRetrofitComponenet.create().getMainInterface()
    }
}