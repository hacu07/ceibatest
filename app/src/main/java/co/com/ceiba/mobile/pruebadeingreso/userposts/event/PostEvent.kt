package co.com.ceiba.mobile.pruebadeingreso.userposts.event

import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.userposts.dto.UserPost
import co.com.ceiba.mobile.pruebadeingreso.utils.Constants
import java.io.Serializable
import java.util.ArrayList

data class PostEvent(
        var typeEvent: Int = Constants.CONNECTION_ERROR,
        val posts: ArrayList<UserPost>?,
        val message: Int = R.string.CONNECTION_ERROR_MESSAGE
): Serializable