package co.com.ceiba.mobile.pruebadeingreso.users.model.dao.api;

import android.content.Context;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.users.event.UserEvent;
import co.com.ceiba.mobile.pruebadeingreso.util.Constants;
import co.com.ceiba.mobile.pruebadeingreso.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersAPIDao {
    UsersService usersService = Util.getRetrofit().create(UsersService.class);

    public void getUsers(final Context context, final UsersResponseCallback callback){
        usersService.getUsers().enqueue(new Callback<UserEvent>() {
            @Override
            public void onResponse(Call<UserEvent> call, Response<UserEvent> response) {
                UserEvent userEvent = response.body();

                if (userEvent != null){
                    userEvent.setTypeEvent(
                        userEvent.getUsers().size() > 0 ?
                            Constants.SUCCESS :
                            Constants.DATA_ERROR
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
            public void onFailure(Call<UserEvent> call, Throwable t) {
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
