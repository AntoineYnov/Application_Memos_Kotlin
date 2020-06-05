package com.formationandroid.applicationmemoskotlin.dao

import androidx.room.*
import com.formationandroid.applicationmemoskotlin.dto.MemoDTO


@Dao
abstract class MemoDAO {
    @Query("SELECT * FROM memo")
    abstract fun loadAllMemos(): Array<MemoDTO>

    @Query("SELECT COUNT(*) FROM memo WHERE intitule = :intitule")
    abstract fun countMemosParIntitule(intitule: String?): Long

    @Insert
    abstract fun insert(vararg courses: MemoDTO?)

    @Update
    abstract fun update(vararg courses: MemoDTO?)

    @Delete
    abstract fun delete(vararg courses: MemoDTO?)
}