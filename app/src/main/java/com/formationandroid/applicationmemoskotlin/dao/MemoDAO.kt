package com.formationandroid.applicationmemoskotlin.dao

import androidx.room.*
import com.formationandroid.applicationmemoskotlin.dto.MemoDTO


@Dao
interface MemoDAO {
    @Query("SELECT * FROM memo")
    fun loadAllMemos(): List<MemoDTO>

    @Query("SELECT COUNT(*) FROM memo WHERE intitule = :intitule")
    fun countMemosParIntitule(intitule: String?): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg courses: MemoDTO  ?)

    @Update
    fun update(vararg courses: MemoDTO?)

    @Delete
    fun delete(vararg courses: MemoDTO?)
}