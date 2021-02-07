package fr.isen.mignerot.guinguetteclickandcollect.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class Category (@SerializedName("name_fr") val name: String, @SerializedName("items") val dishes: List<Dish>) : Serializable