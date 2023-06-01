package activities

import crud.CRUD
import adapters.EjerciciosAdapter
import adapters.OnItemClickListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessroutines.R
import com.google.android.material.navigation.NavigationView
import crud.EjercicioCRUD
import crud.EjerciciosDiaCRUD
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
    var ejerciciosDiaCRUD: EjerciciosDiaCRUD = EjerciciosDiaCRUD()

    //------------------------------Atributos y metodos Menu lateral-----------------------------//
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios)

        iniciarMenuLateral()
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

    private fun iniciarMenuLateral() {
        //Inicializar drawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        // Declarar la toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_ejercicios)

        // Configurar la toolbar
        setSupportActionBar(toolbar)

        // Agregar el icono de hamburguesa y quitar titulo
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_icon)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // El icono menu abre el desplegable
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }


        val menu = navigationView.menu
        val nav_home1 = menu.findItem(R.id.nav_item_one)
        val nav_personalizado = menu.findItem(R.id.nav_item_two)
        val nav_eliminar = menu.findItem(R.id.nav_item_three)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                nav_home1.itemId -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }

                nav_personalizado.itemId -> {
                    val intent = Intent(this, DiasActivity::class.java)
                    startActivity(intent)
                    true
                }

                nav_eliminar.itemId -> {
                    showConfirmationDialog()
                    true
                }

                else -> false

            }
        }
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminación de entrenamientos")
        builder.setMessage("¿Estás seguro de que quieres eliminar todos los entrenamientos?")

        builder.setPositiveButton("Sí") { _, _ ->
            vaciar_bd()
            rellenar_bd()
            val intent = Intent(this@EjerciciosActivity, MainActivity::class.java)
            startActivity(intent)
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
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
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}