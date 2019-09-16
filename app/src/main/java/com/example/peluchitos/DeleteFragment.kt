package com.example.peluchitos


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_delete.view.*
import java.lang.ClassCastException

class DeleteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    var interfaz : comunicador ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_delete,container,false)

        var peluche = arguments?.getParcelableArrayList<Peluche>("pelu")
        peluche!!.toMutableList()

        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL,false)

        val pelucheAdapter = PeluchitosAdapter(peluche,this.requireContext())
        recyclerView.adapter = pelucheAdapter

        view.bBorrar.setOnClickListener {
            var idb = view.eBorar.text.toString()

            if (idb.isEmpty()){
                Toast.makeText(context,"Campo vacío", Toast.LENGTH_SHORT).show()
            }
            else{

                val builder = AlertDialog.Builder(this.context)
                builder.setTitle("Eliminar peluche?")
                builder.setMessage(" Id: $idb \n")

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                    interfaz?.deleteItem(idb)
                    view.eBorar.text.clear()
                }

                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(context,"Peluche no borrado ", Toast.LENGTH_SHORT).show()
                }
                builder.show()
            }

        }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            interfaz = context as comunicador
        }catch (e: ClassCastException){
            Log.d("exception", e.toString())
        }
    }
}
