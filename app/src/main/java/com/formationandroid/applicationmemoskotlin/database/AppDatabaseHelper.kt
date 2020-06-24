package com.formationandroid.applicationmemoskotlin.database

import android.content.Context
import androidx.room.Room


class AppDatabaseHelper(context: Context) {


    companion object {
        // Attributs :
        private var databaseHelper: AppDatabaseHelper? = null
        private lateinit var database: AppDatabase

        // Getter instance :
        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            if (databaseHelper == null) {
                databaseHelper =
                    AppDatabaseHelper(context.applicationContext)
            }
            return database
    }
    }

    // Constructeur :
    init {
        database = Room.databaseBuilder(context, AppDatabase::class.java, "memo.db")
            .allowMainThreadQueries().build()
    }
}