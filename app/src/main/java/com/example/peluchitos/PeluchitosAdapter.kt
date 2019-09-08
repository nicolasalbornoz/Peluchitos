package com.example.peluchitos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.template.view.*

class PeluchitosAdapter:  RecyclerView.Adapter<PeluchitosAdapter.PeluchitosViewHolder>{

    private var listaPeluchitos: List<Peluche>? = null
    private var context: Context? = null

    constructor(listPeluchitos: List<Peluche>,context: Context){
        this.listaPeluchitos = listaPeluchitos
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeluchitosViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.template,parent,false)
        return PeluchitosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaPeluchitos?.size!!
    }

    override fun onBindViewHolder(holder: PeluchitosViewHolder, position: Int) {
        var peluchitos = listaPeluchitos!![position]
        holder.loadItem(peluchitos)
    }

    class PeluchitosViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        fun loadItem(peluche:Peluche) {
            itemView.tviNombre.text = peluche.nombre
        }
    }
}