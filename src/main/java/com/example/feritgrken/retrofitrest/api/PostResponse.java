package com.example.feritgrken.retrofitrest.api;

import com.example.feritgrken.retrofitrest.models.Post;

import java.util.List;

public class PostResponse {
    private String error;
    private List<Post> posts;

    public PostResponse(String error, List<Post> posts) {
        this.error = error;
        this.posts = posts;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
