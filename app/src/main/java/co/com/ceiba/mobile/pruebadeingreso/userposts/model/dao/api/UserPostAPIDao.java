package co.com.ceiba.mobile.pruebadeingreso.userposts.model.dao.api;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.userposts.dto.UserPost;
import co.com.ceiba.mobile.pruebadeingreso.userposts.event.PostEvent;
import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.users.event.UserEvent;
import co.com.ceiba.mobile.pruebadeingreso.utils.Constants;
import co.com.ceiba.mobile.pruebadeingreso.utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPostAPIDao {
    private PostServices services = Util.getRetrofit().create(PostServices.class);

    public void getUserPosts(String userId,final PostResponseCallback callback){
        services.getUserPost(userId).enqueue(new Callback<ArrayList<UserPost>>() {
            @Override
            public void onResponse(Call<ArrayList<UserPost>> call, Response<ArrayList<UserPost>> response) {
                ArrayList<UserPost> posts = response.body();

                if (posts != null){
                    int typeEvent = posts.size() > 0 ?
                            Constants.SUCCESS :
                            Constants.DATA_ERROR;
                    int message = typeEvent == Constants.SUCCESS ?
                            R.string.SUCCESS_MESSAGE :
                            R.string.ERROR_DATA_MESSAGE;
                    PostEvent userEvent = new PostEvent(
                            typeEvent,
                            posts,
                            message
                    );
                    callback.onResponse(userEvent);
                }else{
                    callback.onResponse(
                            new PostEvent(
                                    Constants.RESPONSE_ERROR,
                                    null,
                                    R.string.ERROR_RESPONSE_MESSAGE
                            )
                    );
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserPost>> call, Throwable t) {
                callback.onResponse(
                        new PostEvent(
                                Constants.CONNECTION_ERROR,
                                null,
                                R.string.CONNECTION_ERROR_MESSAGE
                        )
                );
            }
        });
    }
}
