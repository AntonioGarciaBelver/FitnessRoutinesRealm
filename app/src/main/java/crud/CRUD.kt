package crud

import com.example.fitnessroutines.R
import io.realm.RealmList
import models.EjercicioR
import models.EjerciciosDia
import models.Musculo

open class CRUD {

    fun getAllMusculos(): MutableList<Musculo> {
        return mutableListOf (
            Musculo(0,"Abdominales", R.drawable.abdominales, getAllEjerciciosAbdominales()),
            Musculo(1,"Biceps", R.drawable.biceps, getAllEjerciciosBiceps()),
            Musculo(2,"Triceps", R.drawable.triceps, getAllEjerciciosTriceps()),
            Musculo(3,"Espalda", R.drawable.espalda, getAllEjerciciosEspalda()),
            Musculo(4,"Pecho", R.drawable.pecho, getAllEjerciciosPecho()),
            Musculo(5,"Piernas", R.drawable.piernas, getAllEjerciciosPiernas()),
            Musculo(6,"Hombros", R.drawable.hombros, getAllEjerciciosHombros()),
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
    fun getAllEjerciciosTriceps(): RealmList<EjercicioR>{
        return RealmList (
            EjercicioR(20,"Pushdowns", R.drawable.triceps_1_1,getAllImagenesTriceps_1(), "0.00", "0",2),
            EjercicioR(21,"Extensiones de Triceps", R.drawable.triceps_2_1, getAllImagenesTriceps_2(), "0.00", "0",2),
            EjercicioR(22,"Press de Banca con Agarre Cerrado", R.drawable.triceps_3_1, getAllImagenesTriceps_3(), "0.00", "0",2),
            EjercicioR(23,"Flexiones Diamante", R.drawable.triceps_4_1, getAllImagenesTriceps_4(), "0.00", "0",2),
            EjercicioR(24,"Fondos Triceps en Banco", R.drawable.triceps_5_1, getAllImagenesTriceps_5(), "0.00", "0",2)
        )
    }
    fun getAllEjerciciosEspalda(): RealmList<EjercicioR>{
        return RealmList (
            EjercicioR(25,"Dominadas", R.drawable.espalda_1_1 ,getAllImagenesEspalda_1(), "0.00", "0",3),
            EjercicioR(26,"Peso Muerto", R.drawable.espalda_2_1 ,getAllImagenesEspalda_2(), "0.00", "0",3),
            EjercicioR(27,"Jalones Laterales", R.drawable.espalda_3_1 ,getAllImagenesEspalda_3(), "0.00", "0",3),
            EjercicioR(28,"Remadas Sentado", R.drawable.espalda_4_1 ,getAllImagenesEspalda_4(), "0.00", "0",3),
            EjercicioR(29,"Extensiones de Espalda", R.drawable.espalda_5_1 ,getAllImagenesEspalda_5(), "0.00", "0",3)
        )
    }
    fun getAllEjerciciosPecho(): RealmList<EjercicioR>{
        return RealmList (
            EjercicioR(30,"Press de Banca", R.drawable.pecho_1_1 ,getAllImagenesPecho_1(), "0.00", "0",4),
            EjercicioR(31,"Peso Inclinado", R.drawable.pecho_2_1 ,getAllImagenesPecho_2(), "0.00", "0",4),
            EjercicioR(32,"Press con Mancuernas", R.drawable.pecho_3_1 ,getAllImagenesPecho_3(), "0.00", "0",4),
            EjercicioR(33,"Fondos en Barra", R.drawable.pecho_4_1 ,getAllImagenesPecho_4(), "0.00", "0",4),
            EjercicioR(34,"Crucifijo Cruzado con Cable", R.drawable.pecho_5_1 ,getAllImagenesPecho_5(), "0.00", "0",4)
        )
    }
    fun getAllEjerciciosPiernas(): RealmList<EjercicioR>{
        return RealmList (
            EjercicioR(35,"Sentadillas", R.drawable.piernas_1_1 ,getAllImagenesPiernas_1(), "0.00", "0",5),
            EjercicioR(36,"Press de Pierna Inclinado", R.drawable.piernas_2_1 ,getAllImagenesPiernas_2(), "0.00", "0",5),
            EjercicioR(37,"Extensiones de Pierna", R.drawable.piernas_3_1 ,getAllImagenesPiernas_3(), "0.00", "0",5),
            EjercicioR(38,"Lunges con Mancuerna", R.drawable.piernas_4_1 ,getAllImagenesPiernas_4(), "0.00", "0",5),
            EjercicioR(39,"Sentadillas Hack", R.drawable.piernas_5_1 ,getAllImagenesPiernas_5(), "0.00", "0",5)
        )
    }
    fun getAllEjerciciosHombros(): RealmList<EjercicioR>{
        return RealmList (
            EjercicioR(40,"Press con Mancuernas Sentado", R.drawable.hombros_1_1 ,getAllImagenesHombros_1(), "0.00", "0",6),
            EjercicioR(41,"Press Frontal Sentado con Barra", R.drawable.hombros_2_1 ,getAllImagenesHombros_2(), "0.00", "0",6),
            EjercicioR(42,"Elevaciones Frontales", R.drawable.hombros_3_1 ,getAllImagenesHombros_3(), "0.00", "0",6),
            EjercicioR(43,"Elevaciones Laterales con Polea Baja", R.drawable.hombros_4_1 ,getAllImagenesHombros_4(), "0.00", "0",6),
            EjercicioR(44,"Elevaciones Pájaro con Mancuernas", R.drawable.hombros_5_1 ,getAllImagenesHombros_5(), "0.00", "0",6)
        )
    }
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

    //------------------------IMAGENES EJERCICIOS TRICEPS-------------------------------------------
    fun getAllImagenesTriceps_1(): RealmList<Int>{
        return RealmList(R.drawable.triceps_1_1, R.drawable.triceps_1_2, R.drawable.triceps_1_3)
    }
    fun getAllImagenesTriceps_2(): RealmList<Int>{
        return RealmList(R.drawable.triceps_2_1, R.drawable.triceps_2_2, R.drawable.triceps_2_3)
    }
    fun getAllImagenesTriceps_3(): RealmList<Int>{
        return RealmList(R.drawable.triceps_3_1, R.drawable.triceps_3_2, R.drawable.triceps_3_3)
    }
    fun getAllImagenesTriceps_4(): RealmList<Int>{
        return RealmList(R.drawable.triceps_4_1, R.drawable.triceps_4_2, R.drawable.triceps_4_3)
    }
    fun getAllImagenesTriceps_5(): RealmList<Int>{
        return RealmList(R.drawable.triceps_5_1, R.drawable.triceps_5_2, R.drawable.triceps_5_3)
    }

    //------------------------IMAGENES EJERCICIOS ESPALDA-------------------------------------------
    fun getAllImagenesEspalda_1(): RealmList<Int>{
        return RealmList(R.drawable.espalda_1_1, R.drawable.espalda_1_2, R.drawable.espalda_1_3)
    }
    fun getAllImagenesEspalda_2(): RealmList<Int>{
        return RealmList(R.drawable.espalda_2_1, R.drawable.espalda_2_2, R.drawable.espalda_2_3)
    }
    fun getAllImagenesEspalda_3(): RealmList<Int>{
        return RealmList(R.drawable.espalda_3_1, R.drawable.espalda_3_2, R.drawable.espalda_3_3)
    }
    fun getAllImagenesEspalda_4(): RealmList<Int>{
        return RealmList(R.drawable.espalda_4_1, R.drawable.espalda_4_2, R.drawable.espalda_4_3)
    }
    fun getAllImagenesEspalda_5(): RealmList<Int>{
        return RealmList(R.drawable.espalda_5_1, R.drawable.espalda_5_2, R.drawable.espalda_5_3)
    }

    //------------------------IMAGENES EJERCICIOS PECHO-------------------------------------------
    fun getAllImagenesPecho_1(): RealmList<Int>{
        return RealmList(R.drawable.pecho_1_1, R.drawable.pecho_1_2, R.drawable.pecho_1_3)
    }
    fun getAllImagenesPecho_2(): RealmList<Int>{
        return RealmList(R.drawable.pecho_2_1, R.drawable.pecho_2_2, R.drawable.pecho_2_3)
    }
    fun getAllImagenesPecho_3(): RealmList<Int>{
        return RealmList(R.drawable.pecho_3_1, R.drawable.pecho_3_2, R.drawable.pecho_3_3)
    }
    fun getAllImagenesPecho_4(): RealmList<Int>{
        return RealmList(R.drawable.pecho_4_1, R.drawable.pecho_4_2, R.drawable.pecho_4_3)
    }
    fun getAllImagenesPecho_5(): RealmList<Int>{
        return RealmList(R.drawable.pecho_5_1, R.drawable.pecho_5_2, R.drawable.pecho_5_3)
    }

    //------------------------IMAGENES EJERCICIOS PIERNAS-------------------------------------------
    fun getAllImagenesPiernas_1(): RealmList<Int>{
        return RealmList(R.drawable.piernas_1_1, R.drawable.piernas_1_2, R.drawable.piernas_1_3)
    }
    fun getAllImagenesPiernas_2(): RealmList<Int>{
        return RealmList(R.drawable.piernas_2_1, R.drawable.piernas_2_2, R.drawable.piernas_2_3)
    }
    fun getAllImagenesPiernas_3(): RealmList<Int>{
        return RealmList(R.drawable.piernas_3_1, R.drawable.piernas_3_2, R.drawable.piernas_3_3)
    }
    fun getAllImagenesPiernas_4(): RealmList<Int>{
        return RealmList(R.drawable.piernas_4_1, R.drawable.piernas_4_2, R.drawable.piernas_4_3)
    }
    fun getAllImagenesPiernas_5(): RealmList<Int>{
        return RealmList(R.drawable.piernas_5_1, R.drawable.piernas_5_2, R.drawable.piernas_5_3)
    }

    //------------------------IMAGENES EJERCICIOS HOMBROS-------------------------------------------
    fun getAllImagenesHombros_1(): RealmList<Int>{
        return RealmList(R.drawable.hombros_1_1, R.drawable.hombros_1_2, R.drawable.hombros_1_3)
    }
    fun getAllImagenesHombros_2(): RealmList<Int>{
        return RealmList(R.drawable.hombros_2_1, R.drawable.hombros_2_2, R.drawable.hombros_2_3)
    }
    fun getAllImagenesHombros_3(): RealmList<Int>{
        return RealmList(R.drawable.hombros_3_1, R.drawable.hombros_3_2, R.drawable.hombros_3_3)
    }
    fun getAllImagenesHombros_4(): RealmList<Int>{
        return RealmList(R.drawable.hombros_4_1, R.drawable.hombros_4_2, R.drawable.hombros_4_3)
    }
    fun getAllImagenesHombros_5(): RealmList<Int>{
        return RealmList(R.drawable.hombros_5_1, R.drawable.hombros_5_2, R.drawable.hombros_5_3)
    }

    fun getDefaultDias(): RealmList<EjerciciosDia> {
        return RealmList(
            EjerciciosDia(0, "Lunes", null),
            EjerciciosDia(1, "Martes", null),
            EjerciciosDia(2, "Miércoles", null),
            EjerciciosDia(3, "Jueves", null),
            EjerciciosDia(4, "Viernes", null),
            EjerciciosDia(5, "Sábado", null),
            EjerciciosDia(6, "Domingo", null)
        )
    }
}