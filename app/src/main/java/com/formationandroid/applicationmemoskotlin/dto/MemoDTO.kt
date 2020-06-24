package com.formationandroid.applicationmemoskotlin.dto


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
class MemoDTO() {
    @PrimaryKey(autoGenerate = true)
    var courseId: Long = 0

    lateinit var intitule: String

    // Autre constructeur :
    constructor(intitule: String) : this() {
        this.intitule = intitule
    }

}