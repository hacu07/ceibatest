package co.com.ceiba.mobile.pruebadeingreso.users;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.users.event.UserEvent;
import co.com.ceiba.mobile.pruebadeingreso.users.model.UsersInteractor;
import co.com.ceiba.mobile.pruebadeingreso.users.model.UsersInteractorClass;
import co.com.ceiba.mobile.pruebadeingreso.users.view.MainActivity;
import co.com.ceiba.mobile.pruebadeingreso.users.view.UsersView;
import co.com.ceiba.mobile.pruebadeingreso.utils.Constants;

public class UsersPresenterClass implements UsersPresenter{
    private UsersView mView = null;
    private UsersInteractor mInteractor;
    private ArrayList<User> mUsers;

    public UsersPresenterClass(MainActivity mView, Context context){
        this.mView = mView;
        mInteractor = new UsersInteractorClass(context);
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
    public void getUsers() {
        if (mView != null){
            mView.showLoading();
            mInteractor.getUsers();
        }
    }

    @Subscribe
    public void onUserEventListener(UserEvent event){
        if (mView != null){
            mView.hideLoading();

            switch (event.getTypeEvent()){
                case Constants.SUCCESS:
                    mView.loadUsers(event.getUsers());
                    break;
                default:
                    mView.showMessage(event.getMessage());
                    break;
            }
        }
     }


}
