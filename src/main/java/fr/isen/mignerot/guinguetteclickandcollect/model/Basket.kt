package fr.isen.mignerot.guinguetteclickandcollect.model

import com.google.gson.annotations.SerializedName
import fr.isen.mignerot.guinguetteclickandcollect.model.ItemBasket
import java.io.Serializable

data class Basket (@SerializedName("items") val items: MutableList<ItemBasket>) : Serializable