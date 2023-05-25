package activities

import adapters.EjerciciosPersonalizadosAdapter
import adapters.OnItemClickListener
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessroutines.R
import crud.CRUD
import crud.EjercicioCRUD
import crud.EjerciciosDiaCRUD
import crud.MusculoCRUD
import models.EjercicioR
import models.EjerciciosDia
import models.Musculo
import org.w3c.dom.Text

class EjerciciosPersonalizadosActivity : AppCompatActivity(), View.OnCreateContextMenuListener {
    private lateinit var ejercicios: MutableList<EjercicioR>
    private lateinit var ejerciciosDia: EjerciciosDia
    private lateinit var dias: MutableList<EjerciciosDia>
    var CRUD: CRUD = CRUD()
    var EjerciciosDiaCRUD: EjerciciosDiaCRUD = EjerciciosDiaCRUD()
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mAdapter: EjerciciosPersonalizadosAdapter
    private var posicion: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios_personalizados)

        var posicionActual = intent.getIntExtra("position", -1)
        val tViewTitulo: TextView = findViewById(R.id.tvDia)
        val btnAñadirEjercicio: Button = findViewById(R.id.btnAñadirEjercicio)
        btnAñadirEjercicio.setOnClickListener {
            val intent =
                Intent(this@EjerciciosPersonalizadosActivity, AllEjerciciosActivity::class.java)
            intent.putExtra("position", posicionActual)
            startActivity(intent)
        }
        dias = CRUD.getDefaultDias()
        tViewTitulo.text = dias[posicionActual].nombre

        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerViewEjerciciosPersonalizados)
        mLayoutManager = LinearLayoutManager(this)
        ejerciciosDia = EjerciciosDiaCRUD.getEjercicioByID(posicionActual)!!
        ejercicios = ejerciciosDia.ejercicios!!

        mAdapter = EjerciciosPersonalizadosAdapter(ejercicios, object : OnItemClickListener {
            override fun OnItemClick(vista: View, position: Int) {

                var ejercicioActualID = ejercicios[position].id

                val intent = Intent(
                    this@EjerciciosPersonalizadosActivity,
                    SliderPersonalizadaActivity::class.java
                )
                intent.putExtra("position", position)
                intent.putExtra("ejercicioID", ejercicioActualID)

                startActivity(intent)

            }

        })

        mRecyclerView.adapter = mAdapter
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        mRecyclerView.layoutManager = mLayoutManager
    }

}