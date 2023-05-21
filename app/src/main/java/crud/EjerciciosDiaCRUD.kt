package crud

import io.realm.Realm
import models.EjerciciosDia

open class EjerciciosDiaCRUD {

    var crud: CRUD = CRUD()

    fun addEjercicio(ejercicio: EjerciciosDia) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            realm.copyToRealmOrUpdate(ejercicio)
        }
        realm.close()
    }

    fun addAllEjercicios(){
        for (ejercicioR in crud.getDefaultDias()){
            addEjercicio(ejercicioR)
        }
    }

    fun getAllEjercicios(): MutableList<EjerciciosDia> {
        val realm = Realm.getDefaultInstance()
        var list = mutableListOf<EjerciciosDia>()
        var ejercicios_list = realm.where(EjerciciosDia::class.java).findAll()
        list.addAll(ejercicios_list)
        return list
    }

    fun getAllEjerciciosByDiaID(diaID: Int): MutableList<EjerciciosDia> {
        val realm = Realm.getDefaultInstance()
        var ejercicios_list = realm.where(EjerciciosDia::class.java)
            .equalTo("id", diaID)
            .findAll()
        realm.close()
        return ejercicios_list.toMutableList()
    }


    fun getEjercicioByID(id: Int): EjerciciosDia? {
        val realm = Realm.getDefaultInstance()
        val persona = realm.where(EjerciciosDia::class.java).equalTo("id", id).findFirst()
        realm.close()
        return persona
    }

    fun actualizarEjercicio(musculo: EjerciciosDia) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            realm.copyToRealmOrUpdate(musculo)
        }
        realm.close()
    }

    fun eliminarEjercicioPorId(id: Int) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val persona = realm.where(EjerciciosDia::class.java).equalTo("id", id).findFirst()
            persona?.deleteFromRealm()
        }
        realm.close()
    }

    fun deleteAllEjerciciosDia() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val musculos = realm.where(EjerciciosDia::class.java).findAll()
            musculos.deleteAllFromRealm()
        }
        realm.close()
    }

}

