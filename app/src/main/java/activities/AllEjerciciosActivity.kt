package activities

import adapters.AllEjerciciosAdapter
import adapters.DiasAdapter
import adapters.OnItemClickListener
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessroutines.R
import crud.CRUD
import crud.EjercicioCRUD
import crud.EjerciciosDiaCRUD
import crud.MusculoCRUD
import io.realm.Realm
import io.realm.RealmList
import models.EjercicioR
import models.EjerciciosDia

class AllEjerciciosActivity : AppCompatActivity() {

    private lateinit var ejercicios: MutableList<EjercicioR>
    private lateinit var ejerciciosDia: MutableList<EjerciciosDia>
    var CRUD: CRUD = CRUD()
    var musculoCRUD: MusculoCRUD = MusculoCRUD()
    var ejercicioCRUD: EjercicioCRUD = EjercicioCRUD()
    var ejerciciosDiaCRUD: EjerciciosDiaCRUD = EjerciciosDiaCRUD()
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var ejerciciosAdapter: AllEjerciciosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_ejercicios)

        var posicionActual = intent.getIntExtra("position", -1)
        var btnAceptar: Button = findViewById(R.id.btnAñadirAceptar)

        var ejercicioActual = ejerciciosDiaCRUD.getEjercicioByID(posicionActual)
        ejercicios = ejercicioCRUD.getAllEjercicios()
        var listaEjercicios = ejercicioActual?.ejercicios


        btnAceptar.setOnClickListener {
            val selectedItems = ejerciciosAdapter.getItemsSelected()
            Realm.getDefaultInstance().executeTransaction { realm ->
                for (item in selectedItems) {
                    listaEjercicios?.add(item)
                }
                ejercicioActual?.ejercicios = listaEjercicios
                if (ejercicioActual != null) {
                    realm.copyToRealmOrUpdate(ejercicioActual)
                }
            }

            val intent =
                Intent(this@AllEjerciciosActivity, EjerciciosPersonalizadosActivity::class.java)
            intent.putExtra("position", posicionActual)

            startActivity(intent)
        }

        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerViewAllEjercicios)
        mLayoutManager = LinearLayoutManager(this)

        ejerciciosAdapter = AllEjerciciosAdapter(ejercicios, object : OnItemClickListener {
            override fun OnItemClick(vista: View, position: Int) {
            }
        })


        mRecyclerView.adapter = ejerciciosAdapter
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        mRecyclerView.layoutManager = mLayoutManager

    }

}