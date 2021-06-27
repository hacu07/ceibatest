package co.com.ceiba.mobile.pruebadeingreso.userposts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import co.com.ceiba.mobile.pruebadeingreso.userposts.event.PostEvent;
import co.com.ceiba.mobile.pruebadeingreso.userposts.model.PostInteractor;
import co.com.ceiba.mobile.pruebadeingreso.userposts.model.PostInteractorClass;
import co.com.ceiba.mobile.pruebadeingreso.userposts.view.PostActivity;
import co.com.ceiba.mobile.pruebadeingreso.userposts.view.PostView;
import co.com.ceiba.mobile.pruebadeingreso.utils.Constants;

public class PostPresenterClass implements PostPresenter{
    private PostView mView;
    private PostInteractor mInteractor;

    public PostPresenterClass(PostActivity mView){
        this.mView = mView;
        mInteractor = new PostInteractorClass();
    }

    @Override
    public void onCreate() {
        if (mView != null){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        mView = null;
    }

    @Override
    public void getPosts(String userId) {
        if (mView != null){
            mView.showLoading();
            mInteractor.getPost(userId);
        }
    }

    @Subscribe
    public void onPostEventListener(PostEvent event){
        if (mView != null){
            mView.hideLoading();

            switch (event.getTypeEvent()){
                case Constants.SUCCESS:
                    mView.loadPosts(event.getPosts());
                    break;
                default:
                    mView.showMessage(event.getMessage());
                    break;
            }
        }
    }
}
