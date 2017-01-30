package nz.co.panpanini.kotlindatabinding

import android.app.Application
import timber.log.Timber

/**
 * Created by matthewvern on 2017/01/27.
 */
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}