package fr.isen.mignerot.guinguetteclickandcollect.model

import com.google.gson.annotations.SerializedName

data class ItemBasket (@SerializedName("quantity") var quantity: Int, @SerializedName("dish") var dish: Dish)