package com.example.peluchitos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_inventario.view.*

class InventarioFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

  //  private lateinit var peluche : Peluche

    var lista_peluche: ArrayList<Peluche> = ArrayList()

    //private var data = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_inventario, container, false)
        var inventario_peluches : MutableList<Peluche> = ArrayList()

        if(arguments != null){

            lista_peluche = arguments!!.getParcelableArrayList(ARG_PARAM)!!

            for(peluche in lista_peluche){
                inventario_peluches.add(Peluche(peluche.nombre,peluche.id,peluche.cantidad,peluche.precio))
            }
        }

        recyclerView = view.findViewById(R.id.recycler) as RecyclerView
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(
            this.context,RecyclerView.VERTICAL, false)

        return view

    }
    //        if(arguments != null){
//           lista_peluche = arguments!!.getParcelableArrayList(ARG_PARAM)!!
//
//            for(peluche in lista_peluche){
//                data = data + peluche.nombre + " " + peluche.id + " " + peluche.cantidad + " " + peluche.precio + "\n"
//            }
//
//        }
    companion object{
        private val ARG_PARAM = "MyObject"

        fun newInstance(lista_peluche: ArrayList<Peluche>):InventarioFragment{
            val inventarioFragment = InventarioFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_PARAM,lista_peluche)
            inventarioFragment.arguments = args
            return inventarioFragment
        }
    }

}