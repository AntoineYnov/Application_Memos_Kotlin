package com.formationandroid.applicationmemoskotlin

import android.app.Application

import com.formationandroid.applicationmemoskotlin.di.AppComponent
import com.formationandroid.applicationmemoskotlin.di.DaggerAppComponent

class DIApplication : Application() {

    companion object {
        private lateinit var instance: DIApplication

        fun getAppComponent(): AppComponent {
            return instance.component
        }
    }

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        component = DaggerAppComponent.builder().application(this).build()
    }

}