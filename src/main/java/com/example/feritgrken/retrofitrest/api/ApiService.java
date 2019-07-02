package com.example.feritgrken.retrofitrest.api;



import com.example.feritgrken.retrofitrest.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("photos")
    Call<List<Post>> getAllPosts();

    @GET("photos/{id}")
    Call<Post> getPosts(
            @Path("id") int postId
    );


}
