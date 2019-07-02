package com.example.feritgrken.retrofitrest.adapter;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.feritgrken.retrofitrest.R;
import com.example.feritgrken.retrofitrest.callbacks.OnPostClickListener;
import com.example.feritgrken.retrofitrest.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private LayoutInflater inflater;
    private List<Post> posts;
    private OnPostClickListener onPostClickListener;

    public PostAdapter(List<Post> posts, OnPostClickListener onPostClickListener) {
        this.posts = posts;
        this.onPostClickListener = onPostClickListener;
    }

    /*
        public PostAdapter(List<Post> posts) {
            this.posts = posts;

            Log.e("Size",""+posts.size());
            // bir bakalım buraya liste geliyor mu?
        }
    */
    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        if(inflater==null)
            inflater=LayoutInflater.from(parent.getContext());
        return new PostHolder(inflater.inflate(R.layout.post_layout,parent,false));
        //post_layout ile ilgili resources hatası var sanki evet
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        final Post item=posts.get(position);
        holder.title.setText(item.getTitle());
        holder.id.setText(item.getId()+"");

        Glide.with(holder.thumbnail)
                .load(item.getThumbnailUrl())
                .into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onPostClickListener!=null)
                {
                    onPostClickListener.onPostClick(item.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (posts==null)
            return 0;


        return posts.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{
    TextView title,id;
    ImageView thumbnail;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            this.title=itemView.findViewById(R.id.post_title);
            this.id=itemView.findViewById(R.id.post_id);
            this.thumbnail=itemView.findViewById(R.id.post_thumbnail);
        }
    }


}
