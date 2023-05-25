package activities

import crud.CRUD
import adapters.EjerciciosAdapter
import adapters.OnItemClickListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessroutines.R
import crud.EjercicioCRUD
import crud.MusculoCRUD
import models.EjercicioR
import models.Musculo

class EjerciciosActivity : AppCompatActivity() {
    private lateinit var musculos: MutableList<Musculo>
    private lateinit var ejercicios: MutableList<EjercicioR>
    private lateinit var mAdapter: RecyclerView.Adapter<EjerciciosAdapter.ViewHolder>
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    var CRUD: CRUD = CRUD()
    var musculoCRUD: MusculoCRUD = MusculoCRUD()
    var ejercicioCRUD: EjercicioCRUD = EjercicioCRUD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios)

        var positionActual = intent.getIntExtra("position", -1)
        var musculo = intent.getStringExtra("musculo")
        musculos = musculoCRUD.getAllMusculos()
        //ejercicios = obtenerEjercicios(positionActual)
        ejercicios = musculo?.let { obtenerEjerciciosByMusculo(it) }!!

        val tvTitulo = findViewById<TextView>(R.id.tvEjercicios)
        tvTitulo.text= musculo
        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerViewEjercicios)
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = EjerciciosAdapter(ejercicios, object : OnItemClickListener {
            override fun OnItemClick(vista: View, position: Int) {

                val intent = Intent(this@EjerciciosActivity, SliderActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("musculo", musculo)

                startActivity(intent)
            }

        })
        mRecyclerView.adapter = mAdapter
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        mRecyclerView.layoutManager = mLayoutManager
    }

    fun obtenerEjerciciosByMusculo(musculo: String):MutableList<EjercicioR>{
        when (musculo) {
            "Abdominales" -> ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(0)
            "Biceps" -> ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(1)
            "Triceps"-> ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(2)
            "Espalda" -> ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(3)
            "Pecho" -> ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(4)
            "Piernas" -> ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(5)
            "Hombros" -> ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(6)
//            7 -> ejercicios = CRUD.getAllEjerciciosAntebrazos()
//            8 -> ejercicios = CRUD.getAllEjerciciosGemelos()
        }
        return ejercicios
    }
}