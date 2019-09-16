package com.example.peluchitos

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_delete.*
import kotlinx.android.synthetic.main.fragment_delete.view.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import java.lang.ClassCastException

class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_search,container,false)

        var peluche = arguments?.getParcelableArrayList<Peluche>("pelu")
        peluche!!.toMutableList()

        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL,false)

        val pelucheAdapter = PeluchitosAdapter(peluche,this.requireContext())
        recyclerView.adapter = pelucheAdapter

        view.bBuscar.setOnClickListener {
            var nombreb = view.eBuscar.text.toString()

            if (nombreb.isEmpty()){
                Toast.makeText(context,"Campo vacío", Toast.LENGTH_SHORT).show()
            }
            else{
               pelucheAdapter.filter.filter(nombreb)
            }
        }
        return view
    }
}

