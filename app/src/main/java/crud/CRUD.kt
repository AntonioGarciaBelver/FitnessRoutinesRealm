package crud

import com.example.fitnessroutines.R
import io.realm.RealmList
import models.EjercicioR
import models.Musculo

open class CRUD {

    fun getAllMusculos(): MutableList<Musculo> {
        return mutableListOf (
            Musculo(0,"Abdominales", R.drawable.abdominales, getAllEjerciciosAbdominales()),
            Musculo(1,"Biceps", R.drawable.biceps, getAllEjerciciosBiceps()),
//            Musculo(2,"TRICEPS", R.drawable.triceps, getAllEjerciciosTriceps()),
//            Musculo(3,"ESPALDA", R.drawable.espalda, getAllEjerciciosEspalda()),
//            Musculo(4,"PECHO", R.drawable.pecho, getAllEjerciciosPecho()),
//            Musculo(5,"PIERNAS", R.drawable.piernas, getAllEjerciciosPiernas()),
//            Musculo(6,"HOMBROS", R.drawable.hombros, getAllEjerciciosHombros()),
//            Musculo(7,"ANTEBRAZOS", R.drawable.antebrazo, getAllEjerciciosAntebrazos()),
//            Musculo(8,"GEMELOS", R.drawable.gemelos, getAllEjerciciosGemelos())
        )
    }
    fun getAllEjerciciosAbdominales(): RealmList<EjercicioR>{
        return RealmList (
            EjercicioR(10,"Sentadillas en Banco Inclinado", R.drawable.abdominales_1_1, getAllImagenesAbdominales_1(), "0.00", "0", 0),
            EjercicioR(11,"Levantamiento de Piernas desde Posición Colgada", R.drawable.abdominales_2_1, getAllImagenesAbdominales_2(), "0.00", "0",0),
            EjercicioR(12,"Inclinaciones Laterales con Mancuernas", R.drawable.abdominales_3_1, getAllImagenesAbdominales_3(), "0.00", "0",0),
            EjercicioR(13,"Abdominales", R.drawable.abdominales_4_1, getAllImagenesAbdominales_4(), "0.00", "0",0),
            EjercicioR(14,"Puente Lateral", R.drawable.abdominales_5_1, getAllImagenesAbdominales_5(), "0.00", "0",0)
        )
    }
    fun getAllEjerciciosBiceps(): RealmList<EjercicioR>{
        return RealmList (
            EjercicioR(15,"Curl", R.drawable.biceps_1_1, getAllImagenesBiceps_1(), "0.00", "0",1),
            EjercicioR(16,"Curl de Martillo", R.drawable.biceps_2_1, getAllImagenesBiceps_2(), "0.00", "0",1),
            EjercicioR(17,"Curl Invertido", R.drawable.biceps_3_1, getAllImagenesBiceps_3(), "0.00", "0",1),
            EjercicioR(18,"Curl del Predicador", R.drawable.biceps_4_1, getAllImagenesBiceps_4(), "0.00", "0",1),
            EjercicioR(19,"Curl de Biceps con cable", R.drawable.biceps_5_1, getAllImagenesBiceps_5(), "0.00", "0",1)
        )
    }
//    fun getAllEjerciciosTriceps(): MutableList<Ejercicio>{
//        return mutableListOf (
//            Ejercicio(20,"Pushdowns", R.drawable.espalda),
//            Ejercicio(21,"Extensiones de Triceps", R.drawable.espalda),
//            Ejercicio(22,"Press de Banca con Agarre Cerrado", R.drawable.espalda),
//            Ejercicio(23,"Flexiones Diamante", R.drawable.espalda),
//            Ejercicio(24,"Fondos con Agarre Cerrado", R.drawable.espalda)
//        )
//    }
//    fun getAllEjerciciosEspalda(): MutableList<Ejercicio>{
//        return mutableListOf (
//            Ejercicio(25,"Dominadas", R.drawable.espalda),
//            Ejercicio(26,"Peso Muerto", R.drawable.espalda),
//            Ejercicio(27,"Jalones Laterales", R.drawable.espalda),
//            Ejercicio(28,"Remadas Sentado", R.drawable.espalda),
//            Ejercicio(29,"Extensiones de Espalda", R.drawable.espalda)
//        )
//    }
//    fun getAllEjerciciosPecho(): MutableList<Ejercicio>{
//        return mutableListOf (
//            Ejercicio(30,"Press de Banca", R.drawable.espalda),
//            Ejercicio(31,"Peso Inclinado", R.drawable.espalda),
//            Ejercicio(32,"Press con Mancuernas", R.drawable.espalda),
//            Ejercicio(33,"Fondos en Barra", R.drawable.espalda),
//            Ejercicio(34,"Crucifijo Cruzado con Cable", R.drawable.espalda)
//        )
//    }
//    fun getAllEjerciciosPiernas(): MutableList<Ejercicio>{
//        return mutableListOf (
//            Ejercicio(35,"Sentadillas", R.drawable.espalda),
//            Ejercicio(36,"Press de Pierna Inclinado", R.drawable.espalda),
//            Ejercicio(37,"Extensiones de Pierna", R.drawable.espalda),
//            Ejercicio(38,"Lunges con Mancuerna", R.drawable.espalda),
//            Ejercicio(39,"Sentadillas Hack", R.drawable.espalda)
//        )
//    }
//    fun getAllEjerciciosHombros(): MutableList<Ejercicio>{
//        return mutableListOf (
//            Ejercicio(40,"Press con Mancuernas Sentado", R.drawable.espalda),
//            Ejercicio(41,"Press Frontal Sentado con Barra", R.drawable.espalda),
//            Ejercicio(42,"Jalones Laterales", R.drawable.espalda),
//            Ejercicio(43,"Elevaciones Laterales con Polea Baja", R.drawable.espalda),
//            Ejercicio(44,"Elevaciones Pájaro con Mancuernas", R.drawable.espalda)
//        )
//    }
//    fun getAllEjerciciosAntebrazos(): MutableList<Ejercicio>{
//        return mutableListOf (
//            Ejercicio(45,"Curl de Muñeca con Barra", R.drawable.espalda),
//            Ejercicio(46,"Curl de Muñeca con Mancuerna", R.drawable.espalda),
//            Ejercicio(47,"Giro de Muñeca con Mancuerna", R.drawable.espalda),
//            Ejercicio(48,"Giro de Muñeca de Pie con Barra", R.drawable.espalda)
//        )
//    }
//    fun getAllEjerciciosGemelos(): MutableList<Ejercicio>{
//        return mutableListOf (
//            Ejercicio(49,"Elevación de Gemelos Sentado", R.drawable.espalda),
//            Ejercicio(50,"Elevaciones Dedos del Pie", R.drawable.espalda),
//            Ejercicio(51,"Elevaciones Dedos del Pie con Una Sola Pierna", R.drawable.espalda),
//            Ejercicio(52,"Saltos de Gemelos con Mancuernas", R.drawable.espalda)
//        )
//    }

    //-------------IMAGENES EJERCICIOS ABDOMINALES--------------------------------------------------
    fun getAllImagenesAbdominales_1(): RealmList<Int> {
        return RealmList(R.drawable.abdominales_6_1, R.drawable.abdominales_6_2,R.drawable.abdominales_6_3, R.drawable.abdominales_6_4)
    }
    fun getAllImagenesAbdominales_2(): RealmList<Int>{
        return RealmList(R.drawable.abdominales_2_1, R.drawable.abdominales_2_2)
    }
    fun getAllImagenesAbdominales_3(): RealmList<Int>{
        return RealmList(R.drawable.abdominales_3_1, R.drawable.abdominales_3_2, R.drawable.abdominales_3_3)
    }
    fun getAllImagenesAbdominales_4(): RealmList<Int>{
        return RealmList(R.drawable.abdominales_4_1, R.drawable.abdominales_4_2)
    }
    fun getAllImagenesAbdominales_5(): RealmList<Int>{
        return RealmList(R.drawable.abdominales_5_1, R.drawable.abdominales_5_2)
    }

    //------------------------IMAGENES EJERCICIOS BICEPS-------------------------------------------
    fun getAllImagenesBiceps_1(): RealmList<Int>{
        return RealmList(R.drawable.biceps_1_1, R.drawable.biceps_1_2, R.drawable.biceps_1_3)
    }
    fun getAllImagenesBiceps_2(): RealmList<Int>{
        return RealmList(R.drawable.biceps_2_1, R.drawable.biceps_2_2, R.drawable.biceps_2_3)
    }
    fun getAllImagenesBiceps_3(): RealmList<Int>{
        return RealmList(R.drawable.biceps_3_1, R.drawable.biceps_3_2, R.drawable.biceps_3_3)
    }
    fun getAllImagenesBiceps_4(): RealmList<Int>{
        return RealmList(R.drawable.biceps_4_1, R.drawable.biceps_4_2, R.drawable.biceps_4_3)
    }
    fun getAllImagenesBiceps_5(): RealmList<Int>{
        return RealmList(R.drawable.biceps_5_1, R.drawable.biceps_5_2)
    }
}