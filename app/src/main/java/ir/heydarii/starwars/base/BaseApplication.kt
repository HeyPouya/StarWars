package ir.heydarii.starwars.base

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ir.heydarii.starwars.base.di.DaggerAppComponent

/**
 * BaseApplication class to provide some needed dependencies
 */
class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

}