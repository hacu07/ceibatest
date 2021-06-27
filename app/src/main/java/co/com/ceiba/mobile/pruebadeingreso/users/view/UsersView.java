package co.com.ceiba.mobile.pruebadeingreso.users.view;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;

public interface UsersView {
    public void showLoading();
    public void hideLoading();
    public void showMessage(int message);
    public void loadUsers(ArrayList<User> users);
    public void loadUsersFiltered(ArrayList<User> users);

    void showSnackbar(int list_is_empty);
}
