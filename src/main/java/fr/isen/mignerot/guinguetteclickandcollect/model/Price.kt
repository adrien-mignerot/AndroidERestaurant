package fr.isen.mignerot.guinguetteclickandcollect.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Price (@SerializedName("price") val price: String) : Serializable