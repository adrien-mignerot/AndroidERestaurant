package fr.isen.mignerot.androiderestaurant.model

import com.google.gson.annotations.SerializedName

data class ItemBasket (@SerializedName("quantity") var quantity: Int, @SerializedName("dish") var dish: Dish)