package co.com.ceiba.mobile.pruebadeingreso.userposts.model;

import org.greenrobot.eventbus.EventBus;

import co.com.ceiba.mobile.pruebadeingreso.userposts.event.PostEvent;
import co.com.ceiba.mobile.pruebadeingreso.userposts.model.dao.api.PostResponseCallback;
import co.com.ceiba.mobile.pruebadeingreso.userposts.model.dao.api.UserPostAPIDao;

public class PostInteractorClass implements PostInteractor{
    private UserPostAPIDao mDao;

    public PostInteractorClass(){
        mDao = new UserPostAPIDao();
    }

    @Override
    public void getPost(String userId) {
        mDao.getUserPosts(userId, new PostResponseCallback() {
            @Override
            public void onResponse(PostEvent postEvent) {
                postEvent(postEvent);
            }
        });
    }

    public void postEvent(PostEvent postEvent){
        EventBus.getDefault().post(postEvent);
    }
}
