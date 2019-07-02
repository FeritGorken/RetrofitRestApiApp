package com.example.feritgrken.retrofitrest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.feritgrken.retrofitrest.api.ApiClient;
import com.example.feritgrken.retrofitrest.api.ApiService;
import com.example.feritgrken.retrofitrest.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailsActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView postContent;
    public static final String POST_ID="post_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        int postID=getIntent().getIntExtra(POST_ID,1);

        postContent=findViewById(R.id.tv_post_content);
        getPostDetails(postID);

    }

    private void getPostDetails(int postID) {
        ApiService service=ApiClient.getService();
        service.getPosts(postID).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                if(response.isSuccessful() && response.body()!=null)
                {
                    Post post;
                    post = response.body();
                    updateUI(post);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                Toast.makeText(PostDetailsActivity.this, "errorr"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void updateUI(Post post) {

        ((CollapsingToolbarLayout) findViewById(R.id.toolbar_layout))
                .setTitle(post.getTitle());
        ImageView thumb=findViewById(R.id.post_thumbnail);
        Glide.with(thumb)
                .load(post.getThumbnailUrl())
                .into(thumb);
        postContent.setText(post.getTitle());
    }
}
