package activities

import crud.CRUD
import adapters.MyAdapter
import adapters.OnItemClickListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessroutines.R
import crud.EjerciciosDiaCRUD
import crud.MusculoCRUD
import models.Musculo


class MainActivity : AppCompatActivity() {
    private lateinit var musculos: MutableList<Musculo>
    private lateinit var mAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    var musculoCRUD: MusculoCRUD = MusculoCRUD()
    var ejerciciosDiaCRUD: EjerciciosDiaCRUD = EjerciciosDiaCRUD()
    var crud: CRUD = CRUD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vaciar_bd()
        rellenar_bd()
        musculos = musculoCRUD.getAllMusculos()


        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val diasButton: ImageView = findViewById(R.id.ivButton)
        diasButton.setOnClickListener{
            val intent = Intent(this@MainActivity, DiasActivity::class.java)
            startActivity(intent)
        }

        mLayoutManager = LinearLayoutManager(this)

        mAdapter = MyAdapter(musculos, object: OnItemClickListener {
            override fun OnItemClick(vista: View, position: Int) {
                var musculo = musculos[position].nombre
                val intent = Intent(this@MainActivity, EjerciciosActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("musculo", musculo)
                startActivity(intent)
            }

        })

        mRecyclerView.adapter=mAdapter
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.itemAnimator= DefaultItemAnimator()
        mRecyclerView.layoutManager=mLayoutManager

    }

    private fun vaciar_bd() {
        musculoCRUD.deleteAllMusculos()
        ejerciciosDiaCRUD.deleteAllEjerciciosDia()
    }

    private fun rellenar_bd() {
        if (musculoCRUD.getAllMusculos().isEmpty()) {
            musculoCRUD.addAllMusculos()
        }
        if(ejerciciosDiaCRUD.getAllEjercicios().isEmpty()){
            ejerciciosDiaCRUD.addAllEjercicios()
        }
    }

}