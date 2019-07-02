package com.example.feritgrken.retrofitrest;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.feritgrken.retrofitrest.adapter.PostAdapter;
import com.example.feritgrken.retrofitrest.api.ApiClient;
import com.example.feritgrken.retrofitrest.api.ApiService;
import com.example.feritgrken.retrofitrest.api.PostResponse;
import com.example.feritgrken.retrofitrest.callbacks.OnPostClickListener;
import com.example.feritgrken.retrofitrest.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnPostClickListener {
    private static final String TAG = "MainActivity";
    private PostAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.posts_recycler);
        getPost();
    }

    private void getPost() {
        ApiService service=ApiClient.getService();

        service.getAllPosts().enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(@NonNull Call<List<Post>> call,@NonNull Response<List<Post>> response) {
                if(response.isSuccessful() && response.body()!=null)
                {
                   /* List<Post> postList = response.body();

                    for (Post post : postList){
                        Log.e("POST",""+post.getTitle());
                    }
*/
                    //Log.e("Response","success");
                    // Calistirirmisin

                    // Ekranı görmüyorum listeyi ne yapıyorsun
                    //verileri çekip
                    //swip yapmaya çalışıyorum
                updateUi(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call,@NonNull Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void updateUi(List<Post> posts) {
        adapter=new PostAdapter(posts,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPostClick(int postId) {
        Intent showDetails=new Intent(this,PostDetailsActivity.class);
        showDetails.putExtra(PostDetailsActivity.POST_ID,postId);
        startActivity(showDetails);
    }
}
