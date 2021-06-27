package co.com.ceiba.mobile.pruebadeingreso.userposts.view;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.userposts.dto.UserPost;

public interface PostView {
    void showLoading();
    void hideLoading();
    void loadPosts(ArrayList<UserPost> posts);
    void showMessage(int message);
}
