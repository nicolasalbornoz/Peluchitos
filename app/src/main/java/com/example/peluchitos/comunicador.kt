package com.example.peluchitos

interface comunicador {
    fun enviarDatos(nombre:String, id:String, cantidad:String, precio:String)
    fun deleteItem (id: String)
}