package com.formationandroid.applicationmemoskotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.formationandroid.applicationmemoskotlin.dao.MemoDAO
import com.formationandroid.applicationmemoskotlin.dto.MemoDTO


@Database(entities = [MemoDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDAO(): MemoDAO
}
