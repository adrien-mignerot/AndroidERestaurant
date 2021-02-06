package fr.isen.mignerot.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OrderResponse(@SerializedName("code")val code: Int) : Serializable