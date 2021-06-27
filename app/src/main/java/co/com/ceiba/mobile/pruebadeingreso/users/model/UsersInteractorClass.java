package co.com.ceiba.mobile.pruebadeingreso.users.model;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.users.event.UserEvent;
import co.com.ceiba.mobile.pruebadeingreso.users.model.dao.api.UsersAPIDao;
import co.com.ceiba.mobile.pruebadeingreso.users.model.dao.api.UsersResponseCallback;
import co.com.ceiba.mobile.pruebadeingreso.users.model.dao.db.UsersDBDao;
import co.com.ceiba.mobile.pruebadeingreso.utils.Constants;

public class UsersInteractorClass implements UsersInteractor{

    private UsersAPIDao apiDao;
    private UsersDBDao dbDao;
    private Context context;

    public UsersInteractorClass(Context context){
        apiDao = new UsersAPIDao();
        dbDao = new UsersDBDao(context);
        this.context = context;
    }

    @Override
    public void getUsers() {
        ArrayList<User> users = dbDao.getUsersFromDatabase();

        if ( users.size() > 0){
            postUser(new UserEvent(
                    Constants.SUCCESS,
                    users,
                    R.string.SUCCESS_MESSAGE
            ));
        }else{
            getUsersFromAPIService();
        }
    }

    private void getUsersFromAPIService() {
        apiDao.getUsers(context, new UsersResponseCallback() {
            @Override
            public void onResponse(UserEvent userEvent) {
                saveUsers(userEvent);
                postUser(userEvent);
            }


        });
    }

    private void saveUsers(UserEvent userEvent) {
        if (userEvent.getUsers() != null){
            dbDao.insertUsers(userEvent.getUsers());
        }
    }

    public void postUser(UserEvent event){
        EventBus.getDefault().post(event);
    }
}
