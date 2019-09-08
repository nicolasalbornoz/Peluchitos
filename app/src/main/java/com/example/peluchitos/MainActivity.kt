package com.example.peluchitos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), comunicador {

    private var lista_peluches: ArrayList<Peluche> = ArrayList()

    private lateinit var peluche : Peluche

    override fun enviarDatos(nombre: String, id: String, cantidad: String, precio: String){
        peluche = Peluche(nombre,id,cantidad,precio)
        lista_peluches.add(peluche)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        when (item.itemId) {
            R.id.navigation_add -> {
                val agregarFragment = AddFragment()
                transaction.replace(R.id.contenedor,agregarFragment).commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_list -> {
                transaction.replace(R.id.contenedor,InventarioFragment.newInstance(lista_peluches)).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        val addFragment = AddFragment()
        transaction.add(R.id.contenedor,addFragment).commit()
    }
}
