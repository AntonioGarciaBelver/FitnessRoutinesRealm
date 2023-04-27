package models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Musculo(
    @PrimaryKey var id: Int = 0,
    var nombre: String? = null,
    var imagen: Int = 0,
    var ejercicios: RealmList<EjercicioR> = RealmList()
) : RealmObject()

