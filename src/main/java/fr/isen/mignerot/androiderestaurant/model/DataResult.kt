package fr.isen.mignerot.androiderestaurant.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class DataResult (@SerializedName("data") val data: List<Category>) : Serializable