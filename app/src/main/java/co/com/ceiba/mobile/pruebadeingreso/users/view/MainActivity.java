package co.com.ceiba.mobile.pruebadeingreso.users.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.users.UsersPresenter;
import co.com.ceiba.mobile.pruebadeingreso.users.UsersPresenterClass;
import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;

public class MainActivity extends Activity implements UsersView {
    ProgressDialog progressDialog;
    UsersPresenter mPresenter;
    RecyclerView recyclerViewSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mPresenter = new UsersPresenterClass(this,this);
        mPresenter.onCreate();
    }

    private void initView() {
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(
                MainActivity.this,
                "",
                "Loading...",
                true,
                false);
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(int message) {
        Toast.makeText(this,getString(message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadUsers(ArrayList<User> users) {

    }
}