package fr.isen.mignerot.guinguetteclickandcollect.model

import org.json.JSONObject
import java.io.Serializable

data class Login(
        val email: String,
        val password: String
) : Serializable {

    fun toLoginParameters( params: JSONObject): JSONObject {
        params.put("email", email)
        params.put("password", password)
        return params
    }
}