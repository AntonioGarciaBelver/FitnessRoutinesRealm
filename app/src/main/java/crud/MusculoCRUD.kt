package crud

import io.realm.Realm
import models.Musculo

open class MusculoCRUD {

    var crud: CRUD = CRUD()

    fun addMusculo(ejercicio: Musculo) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            realm.copyToRealmOrUpdate(ejercicio)
        }
        realm.close()
    }

    fun addAllMusculos(){
        for (musculo in crud.getAllMusculos()){
            addMusculo(musculo)
        }
    }

    fun getAllMusculos(): MutableList<Musculo> {

        val realm = Realm.getDefaultInstance()
        var list = mutableListOf<Musculo>()
        var ejercicios_list = realm.where(Musculo::class.java).findAll()
        list.addAll(ejercicios_list)
        return list
    }


    fun getMusculo(id: Int): Musculo? {
        val realm = Realm.getDefaultInstance()
        val persona = realm.where(Musculo::class.java).equalTo("id", id).findFirst()
        realm.close()
        return persona
    }

    fun actualizarMusculo(musculo: Musculo) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            realm.copyToRealmOrUpdate(musculo)
        }
        realm.close()
    }

    fun eliminarMusculoPorId(id: Int) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val persona = realm.where(Musculo::class.java).equalTo("id", id).findFirst()
            persona?.deleteFromRealm()
        }
        realm.close()
    }

    fun deleteAllMusculos() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val musculos = realm.where(Musculo::class.java).findAll()
            musculos.deleteAllFromRealm()
        }
        realm.close()
    }

}
