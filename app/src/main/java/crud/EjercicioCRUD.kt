package crud

import io.realm.Realm
import models.EjercicioR
import models.Musculo

open class EjercicioCRUD {

    var crud: CRUD = CRUD()

    fun addEjercicio(ejercicio: EjercicioR) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            realm.copyToRealmOrUpdate(ejercicio)
        }
        realm.close()
    }

    fun addAllEjercicios(){
        for (ejercicioR in crud.getAllEjerciciosAbdominales()){
            addEjercicio(ejercicioR)
        }
    }

    fun getEjercicioByID(id: Int): EjercicioR? {
        val realm = Realm.getDefaultInstance()
        val ejercicio = realm.where(EjercicioR::class.java).equalTo("id", id).findFirst()
        realm.close()
        return ejercicio
    }

    fun getAllEjercicios(): MutableList<EjercicioR> {

        val realm = Realm.getDefaultInstance()
        var list = mutableListOf<EjercicioR>()
        var ejercicios_list = realm.where(EjercicioR::class.java).findAll()
        list.addAll(ejercicios_list)
        realm.close()
        return list
    }

    fun getAllEjerciciosByMusculoID(musculoID: Int): MutableList<EjercicioR> {
        val realm = Realm.getDefaultInstance()
        val ejercicios_list = realm.where(EjercicioR::class.java)
            .equalTo("musculoID", musculoID)
            .findAll()
        realm.close()
        return ejercicios_list.toMutableList()
    }

    fun actualizarEjercicio(ejercicio: EjercicioR) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            // Obtener el objeto administrado correspondiente a la persona
            val ejercicioAdmin = realm.where(EjercicioR::class.java)
                .equalTo("id", ejercicio.id)
                .findFirst()

            // Modificar el objeto dentro de la transacción de escritura
            ejercicioAdmin?.repeticiones = ejercicio.repeticiones
            ejercicioAdmin?.peso = ejercicio.peso
        }
        realm.close()
    }


    fun eliminarEjercicioPorId(id: Int) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val persona = realm.where(EjercicioR::class.java).equalTo("id", id).findFirst()
            persona?.deleteFromRealm()
        }
        realm.close()
    }

}
