package com.formationandroid.applicationmemoskotlin.di


import android.app.Application
import com.formationandroid.applicationmemoskotlin.AppModule
import com.formationandroid.applicationmemoskotlin.dao.MemoDAO
import com.formationandroid.applicationmemoskotlin.repository.Repository
import com.formationandroid.applicationmemoskotlin.viewmodels.MemoVIewModels
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(repository: Repository)
}