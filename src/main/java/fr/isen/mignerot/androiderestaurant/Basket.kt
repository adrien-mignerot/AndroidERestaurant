package fr.isen.mignerot.androiderestaurant

import com.google.gson.annotations.SerializedName
import fr.isen.mignerot.androiderestaurant.model.Category
import fr.isen.mignerot.androiderestaurant.model.ItemBasket
import java.io.Serializable

data class Basket (@SerializedName("items") val items: MutableList<ItemBasket>) : Serializable