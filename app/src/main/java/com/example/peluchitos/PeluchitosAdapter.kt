package com.example.peluchitos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.template.view.*

class PeluchitosAdapter:  RecyclerView.Adapter<PeluchitosAdapter.PeluchitosViewHolder>, Filterable{

    private var listaPeluchitos: MutableList<Peluche>? = null
    private var listaPeluchitosFULL: MutableList<Peluche>
    private var context: Context? = null

    constructor(listPeluchitos: MutableList<Peluche>,context: Context){
        this.listaPeluchitos = listPeluchitos
        listaPeluchitosFULL = ArrayList(listPeluchitos)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeluchitosViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.template,parent,false)
        return PeluchitosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaPeluchitos?.size!!
    }

    override fun getFilter(): Filter {

        return object : Filter(){

            override fun performFiltering(charString: CharSequence?): FilterResults {

                val charSearch = charString.toString()
                var FilteredList: MutableList<Peluche> = ArrayList()

                if(charSearch.isEmpty()){

                    FilteredList.addAll(listaPeluchitosFULL)

                }else{
                    for(row: Peluche in listaPeluchitosFULL){
                        if(row.nombre.toLowerCase().contains(charSearch.toLowerCase())){
                            FilteredList.add(row)
                        }
                    }
                }

                val filterResult = Filter.FilterResults()
                filterResult.values = FilteredList
                return filterResult
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                listaPeluchitos?.clear()
                listaPeluchitos = filterResults!!.values as ArrayList<Peluche>
                notifyDataSetChanged()
            }

        }
    }

    override fun onBindViewHolder(holder: PeluchitosViewHolder, position: Int) {
        var peluchitos = listaPeluchitos!![position]
        holder.loadItem(peluchitos)
    }

    class PeluchitosViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        fun loadItem(peluche:Peluche) {
            itemView.tvNombre_stock_texto.text = peluche.nombre
            itemView.tvId_stock_texto.text = peluche.id
            itemView.tvCantidad_stock_texto.text = peluche.cantidad
            itemView.tvPrecio_stock_texto.text = "$" + peluche.precio
        }
    }

}