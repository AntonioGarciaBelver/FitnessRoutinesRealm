package activities

import adapters.EjerciciosPersonalizadosAdapter
import adapters.OnItemClickListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
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
import crud.CRUD
import crud.EjerciciosDiaCRUD
import crud.MusculoCRUD
import models.EjercicioR
import models.EjerciciosDia

class EjerciciosPersonalizadosActivity : AppCompatActivity(), View.OnCreateContextMenuListener {
    private lateinit var ejercicios: MutableList<EjercicioR>
    private lateinit var ejerciciosDia: EjerciciosDia
    private lateinit var dias: MutableList<EjerciciosDia>
    var CRUD: CRUD = CRUD()
    var ejerciciosDiaCRUD: EjerciciosDiaCRUD = EjerciciosDiaCRUD()
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mAdapter: EjerciciosPersonalizadosAdapter
    var musculoCRUD: MusculoCRUD = MusculoCRUD()

    //------------------------------Atributos y metodos Menu lateral-----------------------------//
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios_personalizados)

        iniciarMenuLateral()

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
        ejerciciosDia = ejerciciosDiaCRUD.getEjercicioByID(posicionActual)!!
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
        val toolbar = findViewById<Toolbar>(R.id.toolbar_3)

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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onBackPressed() {
        val intent = Intent(this, DiasActivity::class.java)
        startActivity(intent)
    }

}