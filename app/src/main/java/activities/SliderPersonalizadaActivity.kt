package activities

import crud.CRUD
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessroutines.R
import crud.EjercicioCRUD
import crud.EjerciciosDiaCRUD
import crud.MusculoCRUD
import io.realm.Realm
import io.realm.RealmList
import models.EjercicioR
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import java.text.SimpleDateFormat
import java.util.*

class SliderPersonalizadaActivity : AppCompatActivity() {

    val list = mutableListOf<CarouselItem>()
    private lateinit var ejercicioActual: EjercicioR
    private lateinit var imagenes: RealmList<Int>
    var CRUD: CRUD = CRUD()
    var ejercicioCRUD: EjercicioCRUD = EjercicioCRUD()
    var mes: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)

        val tvPeso = findViewById<TextView>(R.id.tvPeso)
        val tvRepeticiones = findViewById<TextView>(R.id.tvRepeticiones)
        val tvFecha = findViewById<TextView>(R.id.tvFechaElegida)
        val btnAñadir = findViewById<Button>(R.id.btnAñadir)
        val etPeso = findViewById<EditText>(R.id.editTextPeso)
        val etRepeticiones = findViewById<EditText>(R.id.editTextRepeticiones)
        val carousel: ImageCarousel = findViewById(R.id.carousel)
        val diaActual = findViewById<TextView>(R.id.tvDiaActual)
        val mesActual = findViewById<TextView>(R.id.tvMesActual)
        val añoActual = findViewById<TextView>(R.id.tvAñoActual)


        var position = intent.getIntExtra("position", -1)
        var ejercicioId = intent.getIntExtra("ejercicioID", -1)

        ejercicioActual = ejercicioCRUD.getEjercicioByID(ejercicioId)!!
        imagenes = ejercicioActual.imagenes!!

        tvPeso.text = ejercicioActual.peso
        tvRepeticiones.text = ejercicioActual.repeticiones
        if (ejercicioActual.dia != "" && ejercicioActual.mes != "" && ejercicioActual.año != "") {
            diaActual.text = ejercicioActual.dia
            mesActual.text = ejercicioActual.mes
            añoActual.text = ejercicioActual.año
        }

        for (imagen in imagenes) {
            list.add(CarouselItem(imagen))
        }
        carousel.addData(list)

        val tvTitulo = findViewById<TextView>(R.id.tvNombreEjercicio)
        tvTitulo.text = ejercicioActual.nombre
        tvTitulo.isSelected = true

        tvFecha.text = obtenerFechaActual()
        val btnCalendario = findViewById<ImageView>(R.id.btnCalendario)

        btnCalendario.setOnClickListener {
            val cal = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    val fechaSeleccionada = "$dayOfMonth/${monthOfYear + 1}/$year"
                    tvFecha.text = fechaSeleccionada

                    // Actualiza las vistas diaActual, mesActual, añoActual con los valores seleccionados
                    diaActual.text = dayOfMonth.toString()
                    mesActual.text = obtenerMesActual(monthOfYear)
                    añoActual.text = year.toString()
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        btnAñadir.setOnClickListener() {
            tvPeso.text = etPeso.text
            tvRepeticiones.text = etRepeticiones.text

            val realm = Realm.getDefaultInstance()
            val ejercicio = ejercicioCRUD.getEjercicioByID(ejercicioActual.id)

            realm.executeTransaction {
                ejercicio?.peso = tvPeso.text.toString()
                ejercicio?.repeticiones = tvRepeticiones.text.toString()
                ejercicio?.dia = diaActual.text.toString()
                ejercicio?.mes = mesActual.text.toString()
                ejercicio?.año = añoActual.text.toString()

                realm.insertOrUpdate(ejercicio)
            }
            realm.close()

            ejercicioActual = ejercicioCRUD.getEjercicioByID(ejercicioActual.id)!!
            tvPeso.text = ejercicioActual.peso
            tvRepeticiones.text = ejercicioActual.repeticiones

        }

    }

    fun obtenerFechaActual(): String {
        // Obtiene la fecha y hora actual
        val cal = Calendar.getInstance()

        // Formatea la fecha y hora actual en un formato que te guste
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaActual = dateFormat.format(cal.time)

        // Retorna la fecha actual
        return fechaActual
    }

    fun obtenerMesActual(mesActual: Int): String {
        when (mesActual) {
            0 -> mes = "Enero"
            1 -> mes = "Febrero"
            2 -> mes = "Marzo"
            3 -> mes = "Abril"
            4 -> mes = "Mayo"
            5 -> mes = "Junio"
            6 -> mes = "Julio"
            7 -> mes = "Agosto"
            8 -> mes = "Septiembre"
            9 -> mes = "Octubre"
            10 -> mes = "Noviembre"
            11 -> mes = "Diciembre"
        }
        return mes
    }

}
