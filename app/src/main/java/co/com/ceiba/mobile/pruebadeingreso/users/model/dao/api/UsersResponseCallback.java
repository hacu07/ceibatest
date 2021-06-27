package co.com.ceiba.mobile.pruebadeingreso.users.model.dao.api;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.users.event.UserEvent;

public interface UsersResponseCallback {
    public void onResponse(UserEvent userEvent);
}
