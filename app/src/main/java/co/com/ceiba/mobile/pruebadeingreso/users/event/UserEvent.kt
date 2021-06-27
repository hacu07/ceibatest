package co.com.ceiba.mobile.pruebadeingreso.users.event

import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.users.dto.User
import co.com.ceiba.mobile.pruebadeingreso.util.Constants;
import java.io.Serializable
import java.util.*

data class UserEvent(
        var typeEvent: Int = Constants.CONNECTION_ERROR,
        val users: ArrayList<User>,
        val message: Int = R.string.CONNECTION_ERROR_MESSAGE
):Serializable