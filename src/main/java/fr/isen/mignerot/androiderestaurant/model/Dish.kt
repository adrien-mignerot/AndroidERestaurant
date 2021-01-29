package fr.isen.mignerot.androiderestaurant.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class Dish (@SerializedName("name_fr") val title: String, @SerializedName("ingredients") val ingredients: List<Ingredient>, @SerializedName("images") private val pictures: List<String>, @SerializedName("prices") private val prices: List<Price>) : Serializable {
    fun getPrice() = prices[0].price + "€"
    // Une ou l'autre
    fun getFormattedPrice() = prices[0].price + "€"
    fun getFirstPicture() = if (pictures.isNotEmpty() && pictures[0].isNotEmpty()) {
        pictures[0]
    } else {
        null
    }
    fun getAllPictures() = if (pictures.isNotEmpty() && pictures.any { it.isNotEmpty() }) {
        pictures.filter { it.isNotEmpty() }
    } else {
        null
    }
}