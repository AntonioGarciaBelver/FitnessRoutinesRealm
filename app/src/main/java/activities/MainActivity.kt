package activities

import crud.CRUD
import adapters.MyAdapter
import adapters.OnItemClickListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessroutines.R
import crud.MusculoCRUD
import io.realm.Realm
import io.realm.RealmConfiguration
import models.Musculo


class MainActivity : AppCompatActivity() {
    private lateinit var musculos: MutableList<Musculo>
    private lateinit var mAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    var musculoCRUD: MusculoCRUD = MusculoCRUD()
    var crud: CRUD = CRUD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Realm.init(this)
//        val config = RealmConfiguration.Builder()
//            .allowWritesOnUiThread(true)
//            .build()
//        val realm = Realm.getInstance(config)

        musculos = crud.getAllMusculos()
        rellenar_bd()

//        musculos = musculoCRUD.getAllMusculos()


        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerView)
        mLayoutManager = LinearLayoutManager(this)

        mAdapter = MyAdapter(musculos, object: OnItemClickListener {
            override fun OnItemClick(vista: View, position: Int) {
                var musculo = musculos[position].nombre
                val intent = Intent(this@MainActivity, Ejercicios::class.java)
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

    private fun rellenar_bd() {
        if (musculoCRUD.getAllMusculos().isEmpty()) {
            musculoCRUD.addAllMusculos()
        }
    }

}