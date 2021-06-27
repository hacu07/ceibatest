package co.com.ceiba.mobile.pruebadeingreso.userposts.model.dao.api;

import co.com.ceiba.mobile.pruebadeingreso.userposts.event.PostEvent;

public interface PostResponseCallback {
    public void onResponse(PostEvent postEvent);
}
