package co.com.ceiba.mobile.pruebadeingreso.users.model.dao.api;

import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import co.com.ceiba.mobile.pruebadeingreso.users.event.UserEvent;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersService {
    @GET(Endpoints.GET_USERS)
    public Call<UserEvent> getUsers();
}
