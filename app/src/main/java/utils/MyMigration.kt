package utils

import io.realm.DynamicRealm
import io.realm.RealmMigration

class MyMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        // Migración de versión 0 a versión 1
        if (oldVersion == 0L) {
            val schema = realm.schema
            val ejercicioSchema = schema.get("EjercicioR")

            // Agregar el nuevo campo "nombre"
            ejercicioSchema!!.addField("musculoID", Int::class.java)
        }
    }
}
