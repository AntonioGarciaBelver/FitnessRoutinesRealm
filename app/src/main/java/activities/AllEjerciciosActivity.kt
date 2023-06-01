package activities

import adapters.AllEjerciciosAdapter
import adapters.DiasAdapter
import adapters.OnItemClickListener
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
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
import crud.EjercicioCRUD
import crud.EjerciciosDiaCRUD
import crud.MusculoCRUD
import io.realm.Realm
import io.realm.RealmList
import models.EjercicioR
import models.EjerciciosDia
import org.w3c.dom.Text

class AllEjerciciosActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var ejercicios: MutableList<EjercicioR>
    private lateinit var ejerciciosDia: MutableList<EjerciciosDia>
    var CRUD: CRUD = CRUD()
    var musculoCRUD: MusculoCRUD = MusculoCRUD()
    var ejercicioCRUD: EjercicioCRUD = EjercicioCRUD()
    var ejerciciosDiaCRUD: EjerciciosDiaCRUD = EjerciciosDiaCRUD()
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var ejerciciosAdapter: AllEjerciciosAdapter

    //------------------------------Atributos y metodos Menu lateral-----------------------------//
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_ejercicios)

        iniciarMenuLateral()

        var posicionActual = intent.getIntExtra("position", -1)
        var btnAceptar: Button = findViewById(R.id.btnAñadirAceptar)

        var ejercicioActual = ejerciciosDiaCRUD.getEjercicioByID(posicionActual)
        ejercicios = ejercicioCRUD.getAllEjercicios()
        var listaEjercicios = ejercicioActual?.ejercicios
        var spinner: Spinner= findViewById(R.id.spinner)

        var adapter = ArrayAdapter.createFromResource(this, R.array.musculos, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var tvTitulo: TextView = findViewById(R.id.tvTituloEjercicios)
        when(position) {
            0 -> {
                ejercicios = ejercicioCRUD.getAllEjercicios().toMutableList()
                tvTitulo.text = "Todos los ejercicios"
            }
            1 -> {
                ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(0).toMutableList()
                tvTitulo.text = "Abdominales"
            }
            2 -> {
                ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(1).toMutableList()
                tvTitulo.text = "Biceps"
            }
            3 -> {
                ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(2).toMutableList()
                tvTitulo.text = "Triceps"
            }
            4 -> {
                ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(3).toMutableList()
                tvTitulo.text = "Espalda"
            }
            5 -> {
                ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(4).toMutableList()
                tvTitulo.text = "Pecho"
            }
            6 -> {
                ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(5).toMutableList()
                tvTitulo.text = "Piernas"
            }
            7 -> {
                ejercicios = ejercicioCRUD.getAllEjerciciosByMusculoID(6).toMutableList()
                tvTitulo.text = "Hombros"
            }
        }
        ejerciciosAdapter.actualizarListaEjercicios(ejercicios)
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        ejercicios = ejercicioCRUD.getAllEjercicios().toMutableList()
        ejerciciosAdapter.actualizarListaEjercicios(ejercicios)
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
        val toolbar = findViewById<Toolbar>(R.id.toolbar_4)

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