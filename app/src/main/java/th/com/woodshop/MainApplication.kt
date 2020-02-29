package th.com.woodshop

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import th.com.woodshop.di.*

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(appModule, databaseModule, repositoryModule, useCaseModule, viewModelModule))
        }
    }
}