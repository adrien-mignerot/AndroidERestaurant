package fr.isen.mignerot.androiderestaurant.model

import org.json.JSONObject
import java.io.Serializable

data class Register(
        val lastName: String,
        val firstName: String,
        val adresse: String,
        val email: String,
        val password: String
) : Serializable {

    fun toRegisterParameters( params: JSONObject): JSONObject {
        params.put("firstname", firstName)
        params.put("lastname", lastName)
        params.put("adresse", adresse)
        params.put("email", email)
        params.put("password", password)
        return params
    }
}