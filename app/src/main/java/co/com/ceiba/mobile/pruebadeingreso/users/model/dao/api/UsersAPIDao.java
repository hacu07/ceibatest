package co.com.ceiba.mobile.pruebadeingreso.users.model.dao.api;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.users.event.UserEvent;
import co.com.ceiba.mobile.pruebadeingreso.util.Constants;
import co.com.ceiba.mobile.pruebadeingreso.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersAPIDao {
    UsersService usersService = Util.getRetrofit().create(UsersService.class);

    public void getUsers(final Context context, final UsersResponseCallback callback){
        usersService.getUsers().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ArrayList<User> users = response.body();

                if (users != null){
                    int typeEvent = users.size() > 0 ?
                            Constants.SUCCESS :
                            Constants.DATA_ERROR;
                    int message = typeEvent == Constants.SUCCESS ?
                            R.string.SUCCESS_MESSAGE :
                            R.string.ERROR_DATA_MESSAGE;
                    UserEvent userEvent = new UserEvent(
                            typeEvent,
                            users,
                            message
                    );
                    callback.onResponse(userEvent);
                }else{
                    callback.onResponse(
                        new UserEvent(
                            Constants.RESPONSE_ERROR,
                            null,
                            R.string.ERROR_RESPONSE_MESSAGE
                        )
                    );
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                callback.onResponse(
                        new UserEvent(
                            Constants.CONNECTION_ERROR,
                            null,
                            R.string.CONNECTION_ERROR_MESSAGE
                        )
                );
            }
        });
    }
}
