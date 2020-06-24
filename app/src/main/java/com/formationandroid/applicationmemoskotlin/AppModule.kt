package com.formationandroid.applicationmemoskotlin


import android.app.Application
import android.content.Context
import com.formationandroid.applicationmemoskotlin.dao.MemoDAO
import com.formationandroid.applicationmemoskotlin.database.AppDatabaseHelper
import com.formationandroid.applicationmemoskotlin.repository.Repository
import com.formationandroid.applicationmemoskotlin.viewmodels.MemoVIewModels
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    companion object {

        @Provides
        fun provideContext(application: Application): Context {
            return application
        }

        @Singleton
        @Provides
        fun provideDAO(context: Context): MemoDAO {
            return AppDatabaseHelper.getDatabase(context).memoDAO()
        }
    /*
        @Singleton
        @Provides
        fun provideRepository(): Repository? {
            return Repository()
        }

        @Singleton
        @Provides
        fun provideMemoVIewModels(): MemoVIewModels? {
            return MemoVIewModels()
        }
    */
    }

}