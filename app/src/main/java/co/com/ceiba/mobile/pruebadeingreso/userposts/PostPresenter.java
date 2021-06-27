package co.com.ceiba.mobile.pruebadeingreso.userposts;

public interface PostPresenter {
    void onCreate();
    void onDestroy();
    void getPosts(String userId);
}
