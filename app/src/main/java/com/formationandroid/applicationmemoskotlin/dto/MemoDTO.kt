package com.formationandroid.applicationmemoskotlin.dto


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
class MemoDTO {
    @PrimaryKey(autoGenerate = true)
    var courseId: Long = 0

    var intitule: String? = null

    // Constructeur public vide (obligatoire si autre constructeur existant) :
    constructor() {}

    // Autre constructeur :
    constructor(intitule: String?) {
        this.intitule = intitule
    }

}