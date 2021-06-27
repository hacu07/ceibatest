package co.com.ceiba.mobile.pruebadeingreso.userposts.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.userposts.PostPresenter;
import co.com.ceiba.mobile.pruebadeingreso.userposts.PostPresenterClass;
import co.com.ceiba.mobile.pruebadeingreso.userposts.dto.UserPost;
import co.com.ceiba.mobile.pruebadeingreso.userposts.view.adapters.PostAdapter;
import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.users.view.MainActivity;

public class PostActivity extends Activity implements PostView{
    User mUser;
    ProgressDialog progressDialog;
    PostPresenter mPresenter;
    RecyclerView recyclerViewPostsResults;
    PostAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        initViews();

        mPresenter = new PostPresenterClass(this);
        mPresenter.onCreate();
        mPresenter.getPosts(String.valueOf(mUser.getId()));
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    private void initViews() {
        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.email);
        TextView phone = findViewById(R.id.phone);
        recyclerViewPostsResults = findViewById(R.id.recyclerViewPostsResults);
        recyclerViewPostsResults.setLayoutManager(new LinearLayoutManager(this));

        mUser = (User) getIntent().getSerializableExtra(User.class.getName());

        name.setText(mUser.getName());
        email.setText(mUser.getEmail());
        phone.setText(mUser.getPhone());

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(
                PostActivity.this,
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
    public void loadPosts(ArrayList<UserPost> posts) {
        mAdapter = new PostAdapter(posts);
        recyclerViewPostsResults.setAdapter(mAdapter);
    }

    @Override
    public void showMessage(int message) {
        Toast.makeText(this,getString(message), Toast.LENGTH_SHORT).show();
    }
}
