package com.example.peluchitos
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Peluche (var nombre: String, var id: String, var cantidad: String, var precio: String): Parcelable