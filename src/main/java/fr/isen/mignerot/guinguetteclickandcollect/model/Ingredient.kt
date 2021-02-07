package fr.isen.mignerot.guinguetteclickandcollect.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class Ingredient (@SerializedName("name_fr") val name: String) : Serializable