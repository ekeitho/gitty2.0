package com.ekeitho.gitbasic

import android.app.Application
import com.ekeitho.gitbasic.di.ApplicationComponent
import com.ekeitho.gitbasic.di.ApplicationModule
import com.ekeitho.gitbasic.di.DaggerApplicationComponent

class RepoApplication: Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getAppComponents(): ApplicationComponent {
        return appComponent
    }
}