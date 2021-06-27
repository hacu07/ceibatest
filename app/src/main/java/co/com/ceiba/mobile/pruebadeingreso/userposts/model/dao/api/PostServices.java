package co.com.ceiba.mobile.pruebadeingreso.userposts.model.dao.api;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import co.com.ceiba.mobile.pruebadeingreso.userposts.dto.UserPost;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostServices {
    @GET(Endpoints.GET_POST_USER)
    Call<ArrayList<UserPost>> getUserPost(@Query("userId") String userId);
}
