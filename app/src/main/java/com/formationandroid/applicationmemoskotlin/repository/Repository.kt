package com.formationandroid.applicationmemoskotlin.repository

import com.formationandroid.applicationmemoskotlin.DIApplication
import com.formationandroid.applicationmemoskotlin.dao.MemoDAO
import com.formationandroid.applicationmemoskotlin.dto.MemoDTO
import javax.inject.Inject


class Repository() {

    // Injections :
    @Inject
    lateinit var memoDAO: MemoDAO

    init {
        DIApplication.getAppComponent().inject(this)
    }

    // Récupération de données :

    fun getAllMemos(): List<MemoDTO>? {
        return memoDAO.loadAllMemos()
    }

    fun countMemosParIntitule(intitule: String?): Long? {
        return memoDAO.countMemosParIntitule(intitule)
    }

    fun insert(vararg memos: MemoDTO?) {
        memoDAO.insert(*memos);
    }

    fun update(vararg memos: MemoDTO?) {
        memoDAO.update(*memos)
    }

    fun delete(vararg memos: MemoDTO?) {
        memoDAO.delete(*memos);
    }
}