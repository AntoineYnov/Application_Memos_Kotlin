package com.formationandroid.applicationmemoskotlin.viewmodels

import androidx.lifecycle.ViewModel
import com.formationandroid.applicationmemoskotlin.dto.MemoDTO
import com.formationandroid.applicationmemoskotlin.repository.Repository

class MemoVIewModels: ViewModel() {

    private lateinit var repository: Repository

    fun init(mainRepository: Repository) {
        this.repository = mainRepository
    }

    fun getMemos(): List<MemoDTO>? {
        return repository.getAllMemos()
    }
    fun addMemo(memo: MemoDTO) {
        repository.insert(memo)
    }

    fun removeMemo(memo: MemoDTO) {
        repository.delete(memo)
    }
}