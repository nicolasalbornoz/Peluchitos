package com.example.peluchitos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), comunicador {

    var lista_peluches: MutableList<Peluche> = ArrayList()
    private lateinit var peluche : Peluche

    override fun enviarDatos(nombre: String, id: String, cantidad: String, precio: String){
        peluche = Peluche(nombre,id,cantidad,precio)
        lista_peluches.add(peluche)
    }

    override fun deleteItem(idd: String) {
        lista_peluches.removeAll{it.id?.toLowerCase() == idd.toLowerCase()}
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val deleteFragment1 = DeleteFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("pelu", ArrayList<Peluche>(lista_peluches))

        deleteFragment1.arguments = bundle
        transaction.replace(R.id.contenedor,deleteFragment1).commit()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        when (item.itemId) {
            R.id.navigation_add -> {
                val agregarFragment = AddFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("pelu",ArrayList<Peluche>(lista_peluches))
                agregarFragment.arguments = bundle
                transaction.replace(R.id.contenedor,agregarFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search-> {
                val buscarFragment = SearchFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("pelu",ArrayList<Peluche>(lista_peluches))
                buscarFragment.arguments = bundle
                transaction.replace(R.id.contenedor,buscarFragment).commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_list -> {
                val stockFragment = InventarioFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("pelu",ArrayList<Peluche>(lista_peluches))
                stockFragment.arguments = bundle
                transaction.replace(R.id.contenedor,stockFragment).commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_delete ->{
                val deleteFragment1 = DeleteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("pelu", ArrayList<Peluche>(lista_peluches))
                deleteFragment1.arguments = bundle
                transaction.replace(R.id.contenedor,deleteFragment1).commit()
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

        lista_peluches.add(Peluche("Pelu1","001","15","1000"))
        lista_peluches.add(Peluche("Pelu2","002","3","23000"))
        lista_peluches.add(Peluche("Pelu3","003","3","23000"))
        lista_peluches.add(Peluche("Pelu4","004","3","23000"))
        lista_peluches.add(Peluche("Pelu5","005","3","23000"))
        lista_peluches.add(Peluche("Pelu6","006","3","23000"))
        lista_peluches.add(Peluche("Pelu7","007","3","23000"))
        lista_peluches.add(Peluche("Pelu8","008","3","23000"))


    }
}
