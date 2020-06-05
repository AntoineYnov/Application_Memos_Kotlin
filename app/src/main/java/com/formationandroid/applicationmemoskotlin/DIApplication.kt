package com.formationandroid.applicationmemoskotlin

import android.app.Application

import com.formationandroid.applicationmemoskotlin.di.AppComponent
import com.formationandroid.applicationmemoskotlin.di.DaggerAppComponent

class DIApplication : Application() {
    // Attributs :
    private var instance: DIApplication? = null
    private var appComponent: AppComponent? = null

    override fun onCreate() {
        // initialisation :
        super.onCreate()
        instance = this

        // dagger :
        appComponent = DaggerAppComponent.builder().application(this)?.build()
    }

    // Getter singleton :
    fun getAppComponent(): AppComponent? {
        return instance?.appComponent
    }
}