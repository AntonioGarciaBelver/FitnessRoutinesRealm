package activities

import adapters.DiasAdapter
import adapters.OnItemClickListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessroutines.R
import crud.CRUD
import crud.EjercicioCRUD
import crud.EjerciciosDiaCRUD
import crud.MusculoCRUD
import models.EjerciciosDia

class DiasActivity : AppCompatActivity() {

    private lateinit var ejerciciosDia: MutableList<EjerciciosDia>
    var CRUD: CRUD = CRUD()
    var musculoCRUD: MusculoCRUD = MusculoCRUD()
    var ejercicioCRUD: EjercicioCRUD = EjercicioCRUD()
    var EjerciciosDiaCRUD: EjerciciosDiaCRUD = EjerciciosDiaCRUD()
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mAdapter: RecyclerView.Adapter<DiasAdapter.ViewHolder>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dias)

        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerViewDias)
        mLayoutManager = LinearLayoutManager(this)
        ejerciciosDia = EjerciciosDiaCRUD.getAllEjercicios()

        mAdapter = DiasAdapter(ejerciciosDia, object : OnItemClickListener {
            override fun OnItemClick(vista: View, position: Int) {
                val intent = Intent(this@DiasActivity, EjerciciosPersonalizadosActivity::class.java)
                intent.putExtra("position", position)

                startActivity(intent)
            }

        })
        mRecyclerView.adapter = mAdapter
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        mRecyclerView.layoutManager = mLayoutManager

    }
}