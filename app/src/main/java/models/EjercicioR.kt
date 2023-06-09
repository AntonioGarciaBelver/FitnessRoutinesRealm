package models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class EjercicioR(
    @PrimaryKey var id: Int = 0,
    var nombre: String? = null,
    var imagen: Int = 0,
    var imagenes: RealmList<Int>? = null,
    var peso: String? = null,
    var repeticiones: String? = null,
    var musculoID: Int = 0,
    var isSelected: Boolean = false,
    var dia: String = "",
    var mes: String = "",
    var año: String = ""

) : RealmObject()

