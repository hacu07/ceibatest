package co.com.ceiba.mobile.pruebadeingreso.users.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.userposts.view.PostActivity;
import co.com.ceiba.mobile.pruebadeingreso.users.UsersPresenter;
import co.com.ceiba.mobile.pruebadeingreso.users.UsersPresenterClass;
import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.users.view.adapters.OnUserClickListener;
import co.com.ceiba.mobile.pruebadeingreso.users.view.adapters.UserAdapter;

public class MainActivity extends Activity implements UsersView, OnUserClickListener {
    ProgressDialog progressDialog;
    UsersPresenter mPresenter;
    RecyclerView recyclerViewSearchResults;
    UserAdapter mUserAdapter;
    TextView editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mPresenter = new UsersPresenterClass(this, this);
        mPresenter.onCreate();
        mPresenter.getUsers();
    }

    private void initViews() {
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
                getString(R.string.generic_message_progress),
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
    public void showSnackbar(int message) {
        Snackbar.make(editTextSearch, getString(message), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadUsers(ArrayList<User> users) {
        mUserAdapter = new UserAdapter(users,this);
        recyclerViewSearchResults.setAdapter(mUserAdapter);
        configureEditTextSearch();
    }

    @Override
    public void loadUsersFiltered(ArrayList<User> users) {
        mUserAdapter.updateItems(users);
    }

    private void configureEditTextSearch() {
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPresenter.filterUsers(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onUserItemClick(User user) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra(User.class.getName(),user);
        startActivity(intent);
    }
}