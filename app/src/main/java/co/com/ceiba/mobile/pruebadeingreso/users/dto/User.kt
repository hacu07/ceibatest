package co.com.ceiba.mobile.pruebadeingreso.users.dto

import java.io.Serializable

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String
): Serializable{
    companion object{
        val ID = "id"
        val NAME = "name"
        val EMAIL = "email"
        val PHONE = "phone"
        val TABLE_NAME = ""
    }
}