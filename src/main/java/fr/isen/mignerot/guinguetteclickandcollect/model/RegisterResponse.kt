package fr.isen.mignerot.guinguetteclickandcollect.model

import java.io.Serializable

data class RegisterResponse(
        val id: Int,
        val code: String,
        val id_shop: Int
) : Serializable