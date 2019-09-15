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
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    var interfaz: comunicador ?= null
    var boleano:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_add, container, false)
        var peluche = arguments?.getParcelableArrayList<Peluche>("pelu")

        view.bEnviar.setOnClickListener {

            var nombre = view.edNombre.text.toString()
            var id = view.edId.text.toString()
            var cantidad = view.edCantidad.text.toString()
            var precio = view.edPrecio.text.toString()

            if(nombre.isEmpty() || id.isEmpty() || cantidad.isEmpty() || precio.isEmpty()){
                Toast.makeText(context, "Alguno de los espacios está vacío", Toast.LENGTH_SHORT).show()
            }else{
                    val builder = AlertDialog.Builder(this.context)
                    builder.setTitle("Agregar peluche al inventario")
                    builder.setMessage(" Nombre: $nombre \n Id: $id \n Cantidad $cantidad \n Precio: $precio \n")

                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                        Toast.makeText(context,"Peluche agregado", Toast.LENGTH_SHORT).show()
                        interfaz?.enviarDatos(nombre,id,cantidad,precio)
                        view.edId.text.clear()
                        view.edNombre.text.clear()
                        view.edPrecio.text.clear()
                        view.edCantidad.text.clear()
                    }

                    builder.setNegativeButton(android.R.string.no) { dialog, which ->
                        Toast.makeText(context,"Peluche no agregado ", Toast.LENGTH_SHORT).show()
                    }
                    builder.show()

            }
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            interfaz = context!! as comunicador
        }catch (e: ClassCastException){
            Log.d("exception",e.toString())
        }
    }
}
